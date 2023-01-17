package com.iainuk.mysdk.exception;

public class SDKNotInitializedException extends RuntimeException {
    public SDKNotInitializedException(String message) { super(message); }

    public SDKNotInitializedException(String message, Throwable cause) { super(message, cause); }
}
