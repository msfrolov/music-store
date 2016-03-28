package com.epam.msfrolov.musicstore.dao;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool<T> {

    private BlockingQueue<T> deque;

    private ConnectionPool() {
    }

    public ConnectionPool(final int poolSize, Class<T> clazz) {
        deque = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++)
            try {
                T t = clazz.newInstance();
                deque.offer(t);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new ConnectionExcepton("Can not create new connection instance", e);
            }
    }

    public static ConnectionPool getPoolInstance() {
        return Init.INSTANCE;
    }

    public T getConnection() {
        T connection;
        try {
            connection = deque.take();
        } catch (InterruptedException e) {
            throw new ConnectionExcepton("can not take connection instance", e);
        }
        return connection;

    }

    private static class Init {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }

}
