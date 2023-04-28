package com.iainuk.mysdk;

public class RuntimeErrorEvent {
    private final String errorCode;
    private final String errorMessage;

    public RuntimeErrorEvent(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
