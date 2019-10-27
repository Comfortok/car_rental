package com.epam.dao.impl;

import com.epam.dao.Dao;
import com.epam.entity.Status;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StatusDaoImpl implements Dao<Status> {
    @Override
    public void insert(Status entity) throws SQLException, IOException {

    }

    @Override
    public Status update(Status entity) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        return false;
    }

    @Override
    public Status getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Status> getAll() throws SQLException, IOException {
        return null;
    }
}
