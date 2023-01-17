package com.iainuk.mysdk;

public record ErrorMessage (String errorCode, String errorMessageType, String errorComponent,
                            String errorDetails, String errorDescription,
                            String messageVersionNumber) {}
