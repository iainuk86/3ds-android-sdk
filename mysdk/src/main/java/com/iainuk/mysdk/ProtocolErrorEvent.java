package com.iainuk.mysdk;

public record ProtocolErrorEvent (String sdkTransactionID, ErrorMessage errorMessage) {}
