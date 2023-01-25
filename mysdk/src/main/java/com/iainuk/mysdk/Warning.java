package com.iainuk.mysdk;

public class Warning {
    private final String id;
    private final String message;
    private final Severity severity;

    public Warning(String id, String message, Severity severity) {
        this.id = id;
        this.message = message;
        this.severity = severity;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Severity getSeverity() {
        return severity;
    }

    public enum Severity {
        LOW, MEDIUM, HIGH
    }
}
