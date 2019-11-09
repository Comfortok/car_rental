package com.epam.dao;

import com.epam.entity.User;

import java.sql.ResultSet;
import java.util.List;

public interface UserDao extends Dao<User> {
    @Override
    void insert(User entity);

    @Override
    void update(User entity);

    @Override
    boolean deleteById(Long id);

    @Override
    User getById(Long id);

    @Override
    List<User> getAll();

    User getUserInfo(ResultSet resultSet);

    User getByEmail(String email);
}