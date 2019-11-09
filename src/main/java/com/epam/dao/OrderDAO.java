package com.epam.dao;

import com.epam.entity.Order;

import java.sql.ResultSet;
import java.util.List;

public interface OrderDao extends Dao<Order> {
    @Override
    void insert(Order order);

    @Override
    void update(Order order);

    @Override
    boolean deleteById(Long id);

    @Override
    Order getById(Long id);

    @Override
    List<Order> getAll();

    List<Order> getAllByUserId(Long id);

    List<Order> getAllForOperator();

    Order getOrderInfo(ResultSet resultSet);

    Order getOrderByUserAndCar(Order order);
}