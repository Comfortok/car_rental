package com.epam.pool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrepareStatementUseConnectionPooling {
    public static void main(String[] args) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        System.out.println("We've got connection from CP.class");
        System.out.println("CP Size: " + connectionPool.getConnectionBlockingQueue().size());
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("user_id"));
                System.out.println(resultSet.getString("email"));
            }

            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } finally {
            connectionPool.freeConnection(connection);
            System.out.println("We've free/released connection to CP.class");
        }
    }
}