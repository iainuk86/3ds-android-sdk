package com.iainuk.mysdk;

public class ErrorMessage {
    private final String errorCode;
    private final String errorDetails;
    private final String errorComponent;
    private final String errorMessageType;
    private final String errorDescription;
    private final String messageVersionNumber;

    public ErrorMessage(String errorCode, String errorDetails, String errorComponent,
                        String errorMessageType, String errorDescription,
                        String messageVersionNumber) {
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
        this.errorComponent = errorComponent;
        this.errorMessageType = errorMessageType;
        this.errorDescription = errorDescription;
        this.messageVersionNumber = messageVersionNumber;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public String getErrorComponent() {
        return errorComponent;
    }

    public String getErrorMessageType() {
        return errorMessageType;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public String getMessageVersionNumber() {
        return messageVersionNumber;
    }
}
