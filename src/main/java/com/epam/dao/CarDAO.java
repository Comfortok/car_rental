package com.epam.dao;

import com.epam.entity.Car;

import java.sql.ResultSet;
import java.util.List;

public interface CarDao extends Dao<Car> {
    @Override
    void insert(Car car);

    @Override
    void update(Car car);

    @Override
    boolean deleteById(Long id);

    @Override
    Car getById(Long id);

    @Override
    List<Car> getAll();

    List<Car> getAvailable();

    Car getCarInfo(ResultSet resultSet);
}