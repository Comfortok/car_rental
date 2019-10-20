package com.epam.pool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static ConnectionPool connectionPool;
    private BlockingQueue<Connection> connectionBlockingQueue;

    public static ConnectionPool getInstance() {
        System.out.println("getInstance method starts.");
        if (connectionPool == null) {
            try {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
                String driver = resourceBundle.getString("driver");
                String url = resourceBundle.getString("url");
                String user = resourceBundle.getString("user");
                String password = resourceBundle.getString("password");
                String stringPoolSize = resourceBundle.getString("size");
                int poolSize = Integer.parseInt(stringPoolSize);
                connectionPool = new ConnectionPool(driver, url, user, password, poolSize);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return connectionPool;
    }

    private ConnectionPool(String driver, String url, String user, String password, int poolSize) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connectionBlockingQueue = new ArrayBlockingQueue<>(poolSize);
        System.out.println("Queue is created, size: " + connectionBlockingQueue.size());
        for (int i = 0; i < poolSize; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connectionBlockingQueue.offer(connection);
        }
    }

    public Connection getConnection() {
        System.out.println("getCon method starts.");
        Connection connection = null;
        try {
            connection = connectionBlockingQueue.take();
        } catch (InterruptedException e) {
            System.out.println("Fail in getConnection method..." + e);
        }
        return connection;
    }

    public void freeConnection(Connection connection) {
        try {
            if (!connection.isClosed()) {
                System.out.println("Connection hasn't released.");
            } else {
                System.out.println("Connection has released.");
            }
        } catch (SQLException e) {
            System.out.println("Fail in freeConnection method..." + e);
        }
    }

    public BlockingQueue<Connection> getConnectionBlockingQueue() {
        return connectionBlockingQueue;
    }
}