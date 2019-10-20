package com.epam.dao;

import com.epam.pool.ConnectionPool;
import com.epam.entity.Role;
import com.epam.entity.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {

    @Override
    public void insert(User user) {
        System.out.println("insert method starts...");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        connection = connectionPool.getConnection();
        System.out.println("con is: " + connection);
        try {
            System.out.println("try starts...");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(email, password) VALUES(?, ?)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            System.out.println("before ps.executeQuery()... user info: " + user.getEmail() + ", " + user.getPassword());
            preparedStatement.executeUpdate();
            System.out.println("query executed!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("catch in insert(");
        } finally {
            connectionPool.freeConnection(connection);
        }
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException, IOException {
        System.out.println("getAll method starts...");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println("CP.getInstance()");
        Connection connection;
        connection = connectionPool.getConnection();
        System.out.println("C.getCon() " + connection);
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM user")) {
                while (resultSet.next()) {
                    System.out.println("another while...");
                    userList.add(getUserInfo(resultSet));
                }
            }
        } finally {
            System.out.println("finally... freeCon");
            connectionPool.freeConnection(connection);
        }
        System.out.println("userList in getAll: " + userList);
        return userList;
    }

    private User getUserInfo(ResultSet resultSet) throws SQLException {
        System.out.println("getuserinfo method starts...");
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        //Role role = new Role();
        user.setRoleId(resultSet.getInt("role_id"));
        //user.setRole(role);
        System.out.println("getuserinfo ends... with user: " + user);
        return user;
    }

    public User getByEmail(String email) throws SQLException, IOException {
        System.out.println("getByEmail method starts.");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        connection = connectionPool.getConnection();
        User user = new User();
        System.out.println("connection: " + connection.getClientInfo());
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM user where email = ?")) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = getUserInfo(resultSet);
                }
            }
        } finally {
            connection.close();
        }
        return user;
    }
}