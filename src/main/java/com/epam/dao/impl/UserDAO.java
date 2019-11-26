package com.epam.dao.impl;

import com.epam.dao.IUserDAO;
import com.epam.entity.Role;
import com.epam.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.constant.ConstantField.*;

public class UserDAO implements IUserDAO {
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM user where email = ?";
    private static final String SQL_INSERT_NEW_USER = "INSERT INTO user(email, password) VALUES(?, ?)";
    private static final String SELECT_ALL_FROM_USER = "SELECT * FROM user";

    @Override
    public void insert(User user, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(User entity, Connection connection) {
    }

    @Override
    public boolean deleteById(Long id, Connection connection) {
        return false;
    }

    @Override
    public User getById(Long id, Connection connection) {
        return null;
    }

    @Override
    public List<User> getAll(Connection connection) throws SQLException {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_USER)) {
                while (resultSet.next()) {
                    userList.add(getUserInfo(resultSet));
                }
            }
        }
        return userList;
    }

    @Override
    public User getUserInfo(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(RESULTSET_USER_ID));
        user.setEmail(resultSet.getString(USER_EMAIL));
        user.setPassword(resultSet.getString(USER_PASSWORD));
        Role role = new Role();
        role.setId(resultSet.getInt(RESULTSET_ROLE_ID));
        user.setRole(role);
        return user;
    }

    @Override
    public User getByEmail(String email, Connection connection) throws SQLException {
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = getUserInfo(resultSet);
                }
            }
        }
        return user;
    }
}