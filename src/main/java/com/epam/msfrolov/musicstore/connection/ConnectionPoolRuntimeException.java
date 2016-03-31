package com.epam.msfrolov.musicstore.connection;

public class ConnectionPoolRuntimeException extends RuntimeException {
    public ConnectionPoolRuntimeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ConnectionPoolRuntimeException(String s) {
        super(s);
    }
}
