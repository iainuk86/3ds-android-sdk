package com.iainuk.mysdk.exception;

public class SDKRuntimeException extends RuntimeException {
    private final String errorCode;

    public SDKRuntimeException(String message) {
        super(message);
        this.errorCode = "";
    }

    public SDKRuntimeException(Throwable cause) {
        super(cause);
        this.errorCode = "";
    }

    public SDKRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "";
    }

    public SDKRuntimeException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
