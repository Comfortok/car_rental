package com.epam.pool;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
    private static final String BUNDLE_NAME = "jdbc";
    private static final String JDBC_DRIVER = "driver";
    private static final String JDBC_URL = "url";
    private static final String JDBC_USER = "user";
    private static final String JDBC_PASSWORD = "password";
    private static final String POOL_SIZE = "size";
    private static ConnectionPool connectionPool;
    private BlockingQueue<Connection> connectionBlockingQueue;

    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
            String driver = resourceBundle.getString(JDBC_DRIVER);
            String url = resourceBundle.getString(JDBC_URL);
            String user = resourceBundle.getString(JDBC_USER);
            String password = resourceBundle.getString(JDBC_PASSWORD);
            String stringPoolSize = resourceBundle.getString(POOL_SIZE);
            int poolSize = Integer.parseInt(stringPoolSize);
            try {
                connectionPool = new ConnectionPool(driver, url, user, password, poolSize);
            } catch (ClassNotFoundException | SQLException e) {
                LOG.error("Exception has happened while trying to create a new connection pool. ", e);
            }
        }
        return connectionPool;
    }

    private ConnectionPool(String driver, String url, String user, String password, int poolSize)
            throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connectionBlockingQueue = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Connection connection = DriverManager.getConnection(url, user, password);
            try {
                connectionBlockingQueue.put(connection);
            } catch (InterruptedException e) {
                LOG.error("Interrupted exception has happened while trying to put new connection. ", e);
            }
        }
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionBlockingQueue.take();
        } catch (InterruptedException e) {
            LOG.error("InterruptedException has happened while trying to take connection. ", e);
        }
        return connection;
    }

    public void freeConnection(Connection connection) {
        if (connectionPool != null) {
            try {
                connectionBlockingQueue.put(connection);
            } catch (Exception e) {
                LOG.error("Exception has happened while trying to release connection. ", e);
            }
        }
    }
}