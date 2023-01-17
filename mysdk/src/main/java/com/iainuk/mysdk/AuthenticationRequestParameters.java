package com.iainuk.mysdk;

public record AuthenticationRequestParameters (String sdkAppID, String sdkReferenceNumber,
                                               String sdkTransactionID, String sdkEphemeralPublicKey,
                                               String deviceData, String messageVersion) {}
