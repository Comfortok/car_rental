package com.epam.dao;

import com.epam.entity.Car;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CarDao extends Dao<Car> {
    @Override
    void insert(Car car) throws SQLException, IOException;

    @Override
    Car update(Car car) throws SQLException;

    @Override
    boolean deleteById(Long id) throws SQLException;

    @Override
    Car getById(Long id) throws SQLException;

    @Override
    List<Car> getAll() throws SQLException, IOException;

    List<Car> getAvailable() throws SQLException;

    Car getCarInfo(ResultSet resultSet) throws SQLException;
}