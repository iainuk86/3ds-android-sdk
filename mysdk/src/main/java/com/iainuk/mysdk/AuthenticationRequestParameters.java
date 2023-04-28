package com.iainuk.mysdk;

public class AuthenticationRequestParameters {
    private final String sdkAppID;
    private final String deviceData;
    private final String messageVersion;
    private final String sdkTransactionID;
    private final String sdkReferenceNumber;
    private final String sdkEphemeralPublicKey;

    public AuthenticationRequestParameters(String sdkAppID, String deviceData,
                                           String messageVersion, String sdkTransactionID,
                                           String sdkReferenceNumber, String sdkEphemeralPublicKey) {
        this.sdkAppID = sdkAppID;
        this.deviceData = deviceData;
        this.messageVersion = messageVersion;
        this.sdkTransactionID = sdkTransactionID;
        this.sdkReferenceNumber = sdkReferenceNumber;
        this.sdkEphemeralPublicKey = sdkEphemeralPublicKey;
    }

    public String getSdkAppID() {
        return sdkAppID;
    }

    public String getDeviceData() {
        return deviceData;
    }

    public String getMessageVersion() {
        return messageVersion;
    }

    public String getSdkTransactionID() {
        return sdkTransactionID;
    }

    public String getSdkReferenceNumber() {
        return sdkReferenceNumber;
    }

    public String getSdkEphemeralPublicKey() {
        return sdkEphemeralPublicKey;
    }
}
