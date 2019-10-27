package com.epam.dao;

import com.epam.entity.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDao extends Dao<User> {
    @Override
    void insert(User entity) throws SQLException, IOException;

    @Override
    User update(User entity) throws SQLException;

    @Override
    boolean deleteById(Long id) throws SQLException;

    @Override
    User getById(Long id) throws SQLException;

    @Override
    List<User> getAll() throws SQLException, IOException;

    User getUserInfo(ResultSet resultSet) throws SQLException;

    User getByEmail(String email) throws SQLException;
}