package com.epam.dao.impl;

import com.epam.dao.IUserDAO;
import com.epam.entity.Role;
import com.epam.entity.User;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.action.ConstantField.*;

public class UserDAO implements IUserDAO {
    private static final Logger LOG = Logger.getLogger(UserDAO.class);
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM user where email = ?";
    private final String SQL_INSERT_NEW_USER = "INSERT INTO user(email, password) VALUES(?, ?)";
    private final String SELECT_ALL_FROM_USER = "SELECT * FROM user";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void insert(User user) {
        LOG.info("UserDaoImpl.insert()");
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            connectionPool.freeConnection(connection);
        }
    }

    @Override
    public void update(User entity) {
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
    public List<User> getAll() {
        LOG.info("UserDaoImpl.getAll()");
        List<User> userList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_USER)) {
                while (resultSet.next()) {
                    userList.add(getUserInfo(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            connectionPool.freeConnection(connection);
        }
        return userList;
    }

    @Override
    public User getUserInfo(ResultSet resultSet) {
        LOG.info("UserDaoImpl.getUserInfo()");
        User user = new User();
        try {
            user.setId(resultSet.getLong(RESULTSET_USER_ID));
            user.setEmail(resultSet.getString(USER_EMAIL));
            user.setPassword(resultSet.getString(USER_PASSWORD));
            Role role = new Role();
            role.setId(resultSet.getInt(RESULTSET_ROLE_ID));
            user.setRole(role);
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("UserDaoImpl.getByEmail()");
        User user = new User();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = getUserInfo(resultSet);
                }
            }
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            connectionPool.freeConnection(connection);
        }
        return user;
    }
}