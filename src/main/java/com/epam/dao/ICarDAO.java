package com.epam.dao;

import com.epam.entity.Car;
import com.epam.entity.CarCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ICarDAO extends IDAO<Car> {
    List<Car> getAvailable(Connection connection) throws SQLException;

    Car getCarInfo(ResultSet resultSet) throws SQLException;

    void updateImage(Car car, Connection connection) throws SQLException;

    Map<String, Integer> getCarDetails(Connection connection) throws SQLException;

    List<CarCategory> getCarCategory(Connection connection) throws SQLException;

    void updatePrice(CarCategory category, Connection connection) throws SQLException;
}