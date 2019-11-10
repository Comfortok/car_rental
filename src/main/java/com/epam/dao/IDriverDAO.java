package com.epam.dao;

import com.epam.entity.Driver;
import com.epam.entity.Order;

import java.util.List;

public interface IDriverDAO extends IDAO<Driver> {
    @Override
    void insert(Driver entity);

    @Override
    void update(Driver entity);

    @Override
    boolean deleteById(Long id);

    @Override
    Driver getById(Long id);

    @Override
    List<Driver> getAll();

    void insertOrderDriver(Driver driver, Order order);

    void insertDriverInfo(Driver driver);

    Driver getDriverByPhone(Driver driver);
}