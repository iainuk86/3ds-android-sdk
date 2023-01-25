package com.iainuk.mysdk.crypto;

import static com.iainuk.mysdk.B64Utils.urlEncode;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.util.Arrays;
import java.util.Objects;

import com.iainuk.mysdk.exception.SDKRuntimeException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

public final class JWEEncrypter {
    private final PublicKey publicKey;

    public JWEEncrypter(PublicKey publicKey) {
        Objects.requireNonNull(publicKey, "Public key must be provided");
        this.publicKey = publicKey;
    }

    public String encrypt(JSONObject object) {
        return createJWE(object.toString()).toString();
    }

    JWE createJWE(String payload) {
        SecretKey cek = generateCek();
        byte[] header = urlEncode("{\"alg\":\"RSA-OAEP-256\", \"enc\":\"A128GCM\"}"
                .getBytes(StandardCharsets.UTF_8));

        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, cek);
            cipher.updateAAD(header);

            byte[] textAndTag = cipher.doFinal(payload.getBytes());

            // Separate the result into the cipher text and authentication tag parts
            byte[] cipherText = Arrays.copyOfRange(textAndTag, 0, textAndTag.length - 16);
            byte[] authTag = Arrays.copyOfRange(textAndTag, textAndTag.length - 16, textAndTag.length);

            return new JWE.Builder()
                    .setHeader(header)
                    .setEncryptedKey(encryptCek(cek))
                    .setIV(cipher.getIV())
                    .setCipherText(cipherText)
                    .setAuthTag(authTag)
                    .build();
        } catch (Exception e) {
            throw new SDKRuntimeException("Could not create JWE", e);
        }
    }

    byte[] encryptCek(SecretKey cek) {
        try {
            AlgorithmParameters algoParams = AlgorithmParameters.getInstance("OAEP");
            AlgorithmParameterSpec spec = new OAEPParameterSpec("SHA-256", "MGF1",
                    MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);
            algoParams.init(spec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey, algoParams);
            return cipher.doFinal(cek.getEncoded());
        } catch (Exception e) {
            throw new SDKRuntimeException("Error encrypting CEK", e);
        }
    }

    SecretKey generateCek() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            return keyGen.generateKey();
        } catch (Exception e) {
            throw new SDKRuntimeException("Error generating CEK", e);
        }
    }
}
