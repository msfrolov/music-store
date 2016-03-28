package com.epam.msfrolov.musicstore.dao;

public class ConnectionExcepton extends RuntimeException {
    public ConnectionExcepton(String s, Throwable throwable) {
        super(s, throwable);
    }
}
