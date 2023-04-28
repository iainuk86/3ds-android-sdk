package com.iainuk.mysdk;

public class ProtocolErrorEvent {
    private final String sdkTransactionID;
    private final ErrorMessage errorMessage;

    public ProtocolErrorEvent(String sdkTransactionID, ErrorMessage errorMessage) {
        this.sdkTransactionID = sdkTransactionID;
        this.errorMessage = errorMessage;
    }

    public String getSdkTransactionID() {
        return sdkTransactionID;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
