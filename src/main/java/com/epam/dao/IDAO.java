package com.epam.dao;

import com.epam.entity.Entity;

import java.sql.Connection;
import java.util.List;

public interface IDAO<T extends Entity> {
    void insert(T entity, Connection connection);

    void update(T entity, Connection connection);

    boolean deleteById(Long id, Connection connection);

    T getById(Long id, Connection connection);

    List<T> getAll(Connection connection);
}