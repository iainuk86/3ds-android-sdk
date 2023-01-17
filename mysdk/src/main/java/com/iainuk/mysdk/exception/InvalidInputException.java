package com.iainuk.mysdk.exception;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String field) {
        super(String.format("Invalid input for: %s", field)); }

    public InvalidInputException(String message, Throwable cause) { super(message, cause); }
}
