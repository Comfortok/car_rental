package com.epam.dao;

import com.epam.entity.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public interface IOrderDAO extends IDAO<Order> {
    List<Order> getAllByUserId(Long id, Connection connection);

    List<Order> getAllForOperator(Connection connection);

    Order getOrderInfo(ResultSet resultSet);

    Order getOrderByUserAndCar(Order order, Connection connection);

    Order getOrderInfoForOperator(ResultSet resultSet);
}