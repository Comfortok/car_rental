package com.epam.dao;

import com.epam.entity.Entity;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T extends Entity> {
    void insert(T entity);
    void update(T entity);
    boolean deleteById(Long id);
    T getById(Long id);
    List<T> getAll();
}