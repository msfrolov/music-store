package com.epam.msfrolov.musicstore.connection;

public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ConnectionPoolException(String s) {
        super(s);
    }
}
