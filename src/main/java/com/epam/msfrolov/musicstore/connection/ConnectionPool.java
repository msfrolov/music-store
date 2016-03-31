package com.epam.msfrolov.musicstore.connection;

import java.sql.Connection;
import java.util.concurrent.BlockingQueue;

public interface ConnectionPool extends AutoCloseable {
    Connection getConnection() throws ConnectionPoolException;

    @Override
    void close();

    int getNumberConnections();

    int getNumberFreeConnections();

    int getNumberBusyConnections();

}
