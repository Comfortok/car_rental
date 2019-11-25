package com.epam.dao;

import com.epam.entity.Car;
import com.epam.entity.CarCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface ICarDAO extends IDAO<Car> {
    List<Car> getAvailable(Connection connection);

    Car getCarInfo(ResultSet resultSet);

    void updateImage(Car car, Connection connection);

    Map<String, Integer> getCarDetails(Connection connection);

    List<CarCategory> getCarCategory(Connection connection);

    void updatePrice(CarCategory category, Connection connection);
}