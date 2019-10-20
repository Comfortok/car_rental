package com.epam.dao;

import com.epam.entity.Entity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T extends Entity> {
    void insert(T entity) throws SQLException, IOException;
    T update(T entity) throws SQLException;
    boolean deleteById(Long id) throws SQLException;
    T getById(Long id) throws SQLException;
    List<T> getAll() throws SQLException, IOException;
}
