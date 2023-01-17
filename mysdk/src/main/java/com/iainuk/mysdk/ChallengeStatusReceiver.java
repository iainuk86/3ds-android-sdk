package com.iainuk.mysdk;

public interface ChallengeStatusReceiver {
    void completed(CompletionEvent completionEvent);

    void cancelled();

    void timedout();

    void protocolError(ProtocolErrorEvent protocolErrorEvent);

    void runtimeError(RuntimeErrorEvent runtimeErrorEvent);
}
