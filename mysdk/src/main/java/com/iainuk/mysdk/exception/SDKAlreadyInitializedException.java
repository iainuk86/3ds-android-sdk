package com.iainuk.mysdk.exception;

public class SDKAlreadyInitializedException extends RuntimeException {
    public SDKAlreadyInitializedException(String message) { super(message); }

    public SDKAlreadyInitializedException(String message, Throwable cause) { super(message, cause); }
}
