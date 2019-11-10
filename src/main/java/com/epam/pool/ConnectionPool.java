package com.epam.pool;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConnectionPool {
    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
    private static final String BUNDLE_NAME = "jdbc";
    private static final String JDBC_DRIVER = "driver";
    private static final String JDBC_URL = "url";
    private static final String JDBC_USER = "user";
    private static final String JDBC_PASSWORD = "password";
    private static final String POOL_SIZE = "size";
    private static ConnectionPool connectionPool;
    private List<Connection> connectionBlockingQueue;
    private List<Connection> usedConnections = new ArrayList<>();

    public static ConnectionPool getInstance() {
        LOG.debug("ConnectionPool.getInstance()");
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
                LOG.error(e);
                e.printStackTrace();
            }
        }
        return connectionPool;
    }

    private ConnectionPool(String driver, String url, String user, String password, int poolSize)
            throws ClassNotFoundException, SQLException {
        LOG.debug("ConnectionPool constructor creating");
        Class.forName(driver);
        connectionBlockingQueue = new ArrayList<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Connection connection = DriverManager.getConnection(url, user, password);
            connectionBlockingQueue.add(connection);
        }
    }

    public Connection getConnection() {
        LOG.debug("ConnectionPool.getConnection()");
        Connection connection = connectionBlockingQueue.remove(connectionBlockingQueue.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public void freeConnection(Connection connection) {
        LOG.debug("ConnectionPool.freeConnection()");
        if (connectionPool != null) {
            try {
                connectionBlockingQueue.add(connection);
                usedConnections.remove(connection);
            } catch (Exception e) {
                LOG.error(e);
                e.printStackTrace();
            }
        }
    }
}