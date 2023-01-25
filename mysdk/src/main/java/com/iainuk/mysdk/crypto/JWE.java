package com.iainuk.mysdk.crypto;

import static com.iainuk.mysdk.B64Utils.urlEncode;

import androidx.annotation.NonNull;

final class JWE {
    private final byte[] header;
    private final byte[] encryptedKey;
    private final byte[] iv;
    private final byte[] cipherText;
    private final byte[] authTag;

    static final class Builder {
        byte[] header;
        byte[] encryptedKey;
        byte[] iv;
        byte[] cipherText;
        byte[] authTag;

        Builder setHeader(byte[] header) {
            this.header = header;
            return this;
        }

        Builder setEncryptedKey(byte[] encryptedKey) {
            this.encryptedKey = urlEncode(encryptedKey);
            return this;
        }

        Builder setIV(byte[] iv) {
            this.iv = urlEncode(iv);
            return this;
        }

        Builder setCipherText(byte[] cipherText) {
            this.cipherText = urlEncode(cipherText);
            return this;
        }

        Builder setAuthTag(byte[] authTag) {
            this.authTag = urlEncode(authTag);
            return this;
        }

        JWE build() {
            return new JWE(this);
        }
    }

    private JWE(Builder builder) {
        header = builder.header;
        encryptedKey = builder.encryptedKey;
        iv = builder.iv;
        cipherText = builder.cipherText;
        authTag = builder.authTag;
    }

    public byte[] getHeader() {
        return header;
    }

    public byte[] getEncryptedKey() {
        return encryptedKey;
    }

    public byte[] getIv() {
        return iv;
    }

    public byte[] getCipherText() {
        return cipherText;
    }

    public byte[] getAuthTag() {
        return authTag;
    }

    @NonNull
    public String toString() {
        return String.join(".",
                new String(header),
                new String(encryptedKey),
                new String(iv),
                new String(cipherText),
                new String(authTag));
    }
}
