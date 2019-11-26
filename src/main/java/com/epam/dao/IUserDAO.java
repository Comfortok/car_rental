package com.epam.dao;

import com.epam.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IUserDAO extends IDAO<User> {
    User getUserInfo(ResultSet resultSet) throws SQLException;

    User getByEmail(String email, Connection connection) throws SQLException;
}