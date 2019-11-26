package com.epam.dao;

import com.epam.entity.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IOrderDAO extends IDAO<Order> {
    List<Order> getAllByUserId(Long id, Connection connection) throws SQLException;

    List<Order> getAllForOperator(Connection connection) throws SQLException;

    Order getOrderInfo(ResultSet resultSet) throws SQLException;

    Order getOrderByUserAndCar(Order order, Connection connection) throws SQLException;

    Order getOrderInfoForOperator(ResultSet resultSet) throws SQLException;
}