package com.iainuk.mysdk;

public record Warning (String id, String message, Severity severity) {
    public enum Severity {
        LOW, MEDIUM, HIGH
    }
}
