package com.epam.dao;

import com.epam.entity.Driver;
import com.epam.entity.Order;
import com.epam.pool.ConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public interface DriverDao extends Dao<Driver> {
    @Override
    void insert(Driver entity) throws SQLException, IOException;

    @Override
    Driver update(Driver entity) throws SQLException;

    @Override
    boolean deleteById(Long id) throws SQLException;

    @Override
    Driver getById(Long id) throws SQLException;

    @Override
    List<Driver> getAll() throws SQLException, IOException;

    void insertOrderDriver(Driver driver, Order order);

    void insertDriverInfo(Driver driver);

    Driver getDriverByPhone(Driver driver) throws SQLException;
}