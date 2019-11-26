package com.epam.dao;

import com.epam.entity.Driver;
import com.epam.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IDriverDAO extends IDAO<Driver> {
    void insertOrderDriver(Driver driver, Order order, Connection connection) throws SQLException;

    void insertDriverInfo(Driver driver, Connection connection) throws SQLException, ParseException;

    Driver getDriverByPhone(Driver driver, Connection connection) throws SQLException;
}