package com.epam.dao;

import com.epam.entity.Order;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends Dao<Order> {
    @Override
    void insert(Order order) throws SQLException, IOException;

    @Override
    Order update(Order order) throws SQLException;

    @Override
    boolean deleteById(Long id) throws SQLException;

    @Override
    Order getById(Long id) throws SQLException;

    @Override
    List<Order> getAll() throws SQLException, IOException;

    List<Order> getAllByUserId(Long id) throws SQLException, IOException;

    List<Order> getAllForOperator() throws SQLException, IOException;

    Order getOrderInfo(ResultSet resultSet) throws SQLException;

    Order getOrderByUserAndCar(Order order) throws SQLException;
}