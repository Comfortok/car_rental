package com.epam.dao;

import com.epam.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IDAO<T extends Entity> {
    void insert(T entity, Connection connection) throws SQLException, ParseException;

    void update(T entity, Connection connection) throws SQLException;

    boolean deleteById(Long id, Connection connection);

    T getById(Long id, Connection connection) throws SQLException;

    List<T> getAll(Connection connection) throws SQLException;
}