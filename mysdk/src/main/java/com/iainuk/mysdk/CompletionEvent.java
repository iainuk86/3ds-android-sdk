package com.iainuk.mysdk;

public class CompletionEvent {
    private final String sdkTransactionID;
    private final String transactionStatus;

    public CompletionEvent(String sdkTransactionID, String transactionStatus) {
        this.sdkTransactionID = sdkTransactionID;
        this.transactionStatus = transactionStatus;
    }

    public String getSdkTransactionID() {
        return sdkTransactionID;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }
}
