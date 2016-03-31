package com.epam.msfrolov.musicstore.connection;

import com.epam.msfrolov.musicstore.util.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;

public class Runner {
    static volatile int x = 0;
    static Object mutex = new Object();
    private static Logger log = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {

        Properties pr = FileHandler.getProperties("properties/connection.properties");
        try (ConnectionPool pool = new MyConnectionPool(pr)) {
            log.debug("total {}", pool.getNumberConnections());
            log.debug("busy {}", pool.getNumberBusyConnections());
            log.debug("free {}", pool.getNumberFreeConnections());
            //ExecutorService executor = Executors.newFixedThreadPool(2);
            Runnable runner = () -> {
                try (Connection connection = pool.getConnection()) {
                    try (Statement statement = connection.createStatement()) {
                        statement.execute("SELECT * FROM STYLE");
                        ResultSet resultSet = statement.getResultSet();
                        while (resultSet.next()) {
                            int id = resultSet.getInt("ID");

                        }
                        synchronized (mutex) {
                            x++;
                        }
                    }
                } catch (ConnectionPoolException e) {
                    log.debug("ConnectionPoolException message {}", e.getMessage());
                } catch (SQLException e) {
                    e.printStackTrace();
                    log.debug("SQLException", e);
                }
            };
            int threadsNumber = 1000;
            Thread[] threads = new Thread[threadsNumber];
            for (int i = 0; i < threadsNumber; i++) {
                threads[i] = new Thread(runner);
            }
            for (int i = 0; i < threadsNumber; i++) {
                threads[i].start();
            }
            try {
                for (int i = 0; i < threadsNumber; i++) {
                    threads[i].join();
                }
            } catch (Exception e) {
            }

            log.debug("total {}", pool.getNumberConnections());
            log.debug("busy {}", pool.getNumberBusyConnections());
            log.debug("free {}", pool.getNumberFreeConnections());
            BlockingQueue freeQueue = pool.getFreeQueue();
            for (Object connection : freeQueue) {
                try {
                    if (((Connection) connection).isClosed())
                        log.debug("isClosed {}", ((Connection) connection).isClosed());
                } catch (SQLException e) {
                    log.debug("WTF");
                }

            }
        }
        log.debug("0 exit" + x);
    }
}
