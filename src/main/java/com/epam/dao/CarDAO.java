package com.epam.dao;

import com.epam.entity.*;
import com.epam.pool.ConnectionPool;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO implements DAO<Car> {
    public final String SQL_QUERY = "SELECT car.car_id AS carID,  car.is_available_yn AS available, brand.name AS brand, model.name AS model, \n" +
            "car_category.name AS category, car_category.price_per_day AS price \n" +
            "FROM car \n" +
            "JOIN model ON car.model_id = model.model_id\n" +
            "JOIN brand ON brand.brand_id = model.brand_id\n" +
            "JOIN car_category ON car_category.car_category_id = car.car_category_id\n" +
            "ORDER BY car_category.price_per_day";
    public final String SQL_QUERY2 = "SELECT car.car_id AS carID, car.is_available_yn AS available, car_rent.order.start_date AS startDate, " +
            "car_rent.order.end_date AS endDate, brand.name AS brand, model.name AS model, car_category.name AS category, car_category.price_per_day AS price\n" +
            "FROM car\n" +
            "JOIN car_rent.order ON car.car_id = car_rent.order.car_id\n" +
            "JOIN model ON car.model_id = model.model_id\n" +
            "JOIN brand ON brand.brand_id = model.brand_id\n" +
            "JOIN car_category ON car_category.car_category_id = car.car_category_id\n" +
            "ORDER BY car_category.price_per_day;";

    @Override
    public void insert(Car car) throws SQLException, IOException {

    }

    @Override
    public Car update(Car car) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        return false;
    }

    @Override
    public Car getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Car> getAll() throws SQLException, IOException {
        System.out.println("getAll method starts...");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println("CP.getInstance()");
        Connection connection;
        connection = connectionPool.getConnection();
        System.out.println("C.getCon() " + connection);
        List<Car> carList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_QUERY)) {
                while (resultSet.next()) {
                    carList.add(getCarInfo2(resultSet));
                }
            }
        } finally {
            System.out.println("finally... freeCon");
            connectionPool.freeConnection(connection);
        }
        System.out.println("getAll method ends.");
        return carList;
    }

    public List<Car> getAvailable() throws SQLException, IOException {
        System.out.println("getAlvailable method starts...");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        connection = connectionPool.getConnection();
        List<Car> availableCarList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_QUERY2)) {
                while (resultSet.next()) {
                    availableCarList.add(getCarInfo(resultSet));
                }
            }
        } finally {
            System.out.println("finally... freeCon");
            connectionPool.freeConnection(connection);
        }
        System.out.println("getAvailable method ends.");
        return availableCarList;
    }

    private Car getCarInfo(ResultSet resultSet) throws SQLException {
/*        System.out.println("getcarinfo method starts...");
        Car car = new Car();
        car.setId(resultSet.getLong("car_id"));
        car.setRegisteredNumber(resultSet.getString("registered_number"));
        Model model = new Model();
        model.setId(resultSet.getLong("model_id"));
        car.setModel(model);
        Color color = new Color();
        color.setId(resultSet.getInt("color_id"));
        car.setColor(color);
        CarCategory carCategory = new CarCategory();
        carCategory.setId(resultSet.getInt("car_category_id"));
        car.setCategory(carCategory);
        Transmission transmission = new Transmission();
        transmission.setId(resultSet.getInt("transmission_id"));
        car.setTransmission(transmission);
        Body body = new Body();
        body.setId(resultSet.getInt("body_id"));
        car.setBody(body);
        EngineType engineType = new EngineType();
        engineType.setId(resultSet.getInt("engine_type_id"));
        car.setEngineType(engineType);
        car.setHasAirConditioner(resultSet.getString("has_air_conditioner_yn"));
        car.setAvailable(resultSet.getString("is_available_yn"));
        car.setEngineVolume(resultSet.getDouble("engine_volume"));
        car.setBaggageAmount(resultSet.getInt("baggage_amount"));
        car.setPassengerAmount(resultSet.getInt("passenger_amount"));
        car.setFuelConsumption(resultSet.getDouble("fuel_consumption"));
        car.setDoorAmount(resultSet.getInt("door_amount"));
        car.setProductionYear(resultSet.getString("production_year"));
        System.out.println("getcarinfo ends... with car: " + car);
        return car;*/
        System.out.println("getCarInfo starts.");
        Car car = new Car();
        Model model = new Model();
        Brand brand = new Brand();
        brand.setName(resultSet.getString("brand"));
        model.setBrand(brand);
        model.setName(resultSet.getString("model"));
        CarCategory carCategory = new CarCategory();
        carCategory.setName(resultSet.getString("category"));
        carCategory.setPricePerDay(resultSet.getDouble("price"));
        car.setId(resultSet.getLong("carID"));
        car.setAvailable(resultSet.getString("available"));
        car.setModel(model);
        car.setCategory(carCategory);
        car.setStartDate(resultSet.getDate("startDate"));
        car.setEndDate(resultSet.getDate("endDate"));
        System.out.println("getCarInfo ends");
        return car;
    }

    private Car getCarInfo2(ResultSet resultSet) throws SQLException {
        System.out.println("getCarInfo2 starts");
        Car car = new Car();
        Model model = new Model();
        Brand brand = new Brand();
        brand.setName(resultSet.getString("brand"));
        model.setBrand(brand);
        model.setName(resultSet.getString("model"));
        CarCategory carCategory = new CarCategory();
        carCategory.setName(resultSet.getString("category"));
        carCategory.setPricePerDay(resultSet.getDouble("price"));
        car.setId(resultSet.getLong("carID"));
        car.setAvailable(resultSet.getString("available"));
        car.setModel(model);
        car.setCategory(carCategory);
        System.out.println("getCarInfo2 ends");
        return car;
    }
}