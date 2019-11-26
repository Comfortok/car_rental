package com.epam.dao.impl;

import com.epam.dao.ICarDAO;
import com.epam.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.constant.ConstantField.*;

public class CarDAO implements ICarDAO {
    private static final int YEAR_SUBSTRING_BEGIN_INDEX = 0;
    private static final int YEAR_SUBSTRING_END_INDEX = 4;
    private static final String SQL_UPDATE_PRICE = "update car_rent.car_category set price_per_day = ? " +
            "where car_category_id = ?";
    private final String SQL_UPDATE_CAR_NUMBER = "update car_rent.car set registered_number = ?, " +
            "is_available_yn = ?, mileage = ?  where car_id = ?";
    private final String SQL_SELECT_CAR_INFO_ID = "select brand.name as brand_name, brand.brand_id, " +
            "model.name as model_name, model.model_id, color.name as color_name, color.color_id, " +
            "transmission.name as transmission_name, transmission.transmission_id, body.name as body_name, " +
            "body.body_id, engine_type.name as engine_name, engine_type.engine_type_id, " +
            "car_category.car_category_id, car_category.name as car_category_name\n" +
            "from car\n" +
            "inner join model on car.model_id = model.model_id \n" +
            "inner join brand on model.brand_id = brand.brand_id\n" +
            "inner join color on car.color_id = color.color_id\n" +
            "inner join transmission on car.transmission_id = transmission.transmission_id\n" +
            "inner join body on car.body_id = body.body_id\n" +
            "inner join engine_type on car.engine_type_id = engine_type.engine_type_id\n" +
            "inner join car_category on car.car_category_id = car_category.car_category_id";
    private final String SQL_INSERT_NEW_CAR = "insert into car_rent.car(registered_number, model_id, color_id, " +
            "car_category_id, transmission_id, body_id, engine_type_id, has_air_conditioner_yn, is_available_yn, \n" +
            "engine_volume, baggage_amount, passenger_amount, fuel_consumption, door_amount, \n" +
            "production_year, image, mileage) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE_CAR_IMAGE = "update car_rent.car set image = ? where car_id = ?";
    private final String SQL_SELECT_CARS_WITH_DATES = "SELECT car.car_id AS carID, car.is_available_yn AS available, " +
            "car_rent.order.start_date AS startDate, car_rent.order.end_date AS endDate, brand.name AS brand, " +
            "model.name AS model, car_category.name AS category, car_category.price_per_day AS carPrice, car.image, " +
            "transmission.name as transmission_name, car.production_year, engine_type.name AS engine_type_name " +
            "FROM car\n" +
            "JOIN car_rent.order ON car.car_id = car_rent.order.car_id\n" +
            "JOIN model ON car.model_id = model.model_id\n" +
            "JOIN brand ON brand.brand_id = model.brand_id\n" +
            "JOIN car_category ON car_category.car_category_id = car.car_category_id\n" +
            "JOIN transmission ON car.transmission_id = transmission.transmission_id\n" +
            "JOIN engine_type ON car.engine_type_id = engine_type.engine_type_id\n" +
            "ORDER BY car_category.price_per_day;";
    private final String SQL_SELECT_ALL_FROM_CAR = "SELECT car.car_id as carId, car.registered_number, model.brand_id, " +
            "brand.name as brand_name, model.model_id, model.name as model_name, car.color_id, color.name as color_name, " +
            "car.car_category_id, car_category.name as car_category_name, car_category.price_per_day, car.transmission_id, " +
            "transmission.name as transmission_name, car.body_id, body.name as body_name,\n" +
            "car.engine_type_id, engine_type.name as engine_type_name, has_air_conditioner_yn, is_available_yn,\n" +
            "engine_volume, baggage_amount, passenger_amount, fuel_consumption, door_amount, production_year,\n" +
            "image, mileage\n" +
            "FROM car\n" +
            "JOIN model ON car.model_id = model.model_id\n" +
            "JOIN brand ON brand.brand_id = model.brand_id\n" +
            "JOIN color ON color.color_id = car.color_id\n" +
            "JOIN transmission ON transmission.transmission_id = car.transmission_id\n" +
            "JOIN body ON body.body_id = car.body_id\n" +
            "JOIN engine_type ON engine_type.engine_type_id = car.engine_type_id\n" +
            "JOIN car_category ON car_category.car_category_id = car.car_category_id\n" +
            "ORDER BY brand.name";
    private static final String SQL_SELECT_ALL_FROM_CATEGORY = "SELECT * FROM car_rent.car_category";

    @Override
    public void insert(Car car, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_CAR)) {
            preparedStatement.setString(1, car.getRegisteredNumber());
            preparedStatement.setLong(2, car.getModel().getId());
            preparedStatement.setLong(3, car.getColor().getId());
            preparedStatement.setLong(4, car.getCategory().getId());
            preparedStatement.setLong(5, car.getTransmission().getId());
            preparedStatement.setLong(6, car.getBody().getId());
            preparedStatement.setLong(7, car.getEngineType().getId());
            preparedStatement.setString(8, car.isHasAirConditioner());
            preparedStatement.setString(9, car.getIsAvailable());
            preparedStatement.setDouble(10, car.getEngineVolume());
            preparedStatement.setInt(11, car.getBaggageAmount());
            preparedStatement.setInt(12, car.getPassengerAmount());
            preparedStatement.setDouble(13, car.getFuelConsumption());
            preparedStatement.setInt(14, car.getDoorAmount());
            preparedStatement.setString(15, car.getProductionYear());
            preparedStatement.setString(16, car.getImageName());
            preparedStatement.setInt(17, car.getMileage());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(Car car, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CAR_NUMBER)) {
            preparedStatement.setString(1, car.getRegisteredNumber());
            preparedStatement.setString(2, car.getIsAvailable());
            preparedStatement.setInt(3, car.getMileage());
            preparedStatement.setLong(4, car.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Car> getAll(Connection connection) throws SQLException {
        List<Car> carList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_CAR)) {
                while (resultSet.next()) {
                    carList.add(getCarInfo(resultSet));
                }
            }
        }
        return carList;
    }

    @Override
    public List<Car> getAvailable(Connection connection) throws SQLException {
        List<Car> availableCarList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_CARS_WITH_DATES)) {
                while (resultSet.next()) {
                    Car car = new Car();
                    car.setId(resultSet.getLong(CAR_ID));
                    car.setAvailable(resultSet.getString(CAR_AVAILABILITY));
                    car.setStartDate(resultSet.getDate(ORDER_START_DATE));
                    car.setEndDate(resultSet.getDate(ORDER_END_DATE));
                    Brand brand = new Brand();
                    Model model = new Model();
                    brand.setName(resultSet.getString(CAR_BRAND));
                    model.setBrand(brand);
                    model.setName(resultSet.getString(CAR_MODEL));
                    car.setModel(model);
                    CarCategory carCategory = new CarCategory();
                    carCategory.setName(resultSet.getString(CAR_CATEGORY));
                    carCategory.setPricePerDay(resultSet.getDouble(CAR_PRICE));
                    car.setCategory(carCategory);
                    car.setImageName(resultSet.getString(CAR_IMAGE));
                    Transmission transmission = new Transmission();
                    transmission.setName(resultSet.getString(CAR_TRANSMISSION_NAME));
                    car.setTransmission(transmission);
                    String productionYear = resultSet.getString(PRODUCTION_YEAR);
                    car.setProductionYear(productionYear.substring(YEAR_SUBSTRING_BEGIN_INDEX, YEAR_SUBSTRING_END_INDEX));
                    EngineType engineType = new EngineType();
                    engineType.setName(resultSet.getString(ENGINE_TYPE_NAME));
                    car.setEngineType(engineType);
                    availableCarList.add(car);
                }
            }
        }
        return availableCarList;
    }

    @Override
    public Car getCarInfo(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong(CAR_ID));
        car.setRegisteredNumber(resultSet.getString(REGISTERED_NUMBER));
        Brand brand = new Brand();
        brand.setId(resultSet.getLong(BRAND_ID));
        brand.setName(resultSet.getString(BRAND_NAME));
        Model model = new Model();
        model.setId(resultSet.getLong(MODEL_ID));
        model.setName(resultSet.getString(MODEL_NAME));
        model.setBrand(brand);
        car.setModel(model);
        Color color = new Color();
        color.setId(resultSet.getInt(COLOR_ID));
        color.setName(resultSet.getString(COLOR_NAME));
        car.setColor(color);
        CarCategory carCategory = new CarCategory();
        carCategory.setId(resultSet.getInt(CAR_CATEGORY_ID));
        carCategory.setName(resultSet.getString(CAR_CATEGORY_NAME));
        carCategory.setPricePerDay(resultSet.getDouble(CAR_PRICE_PER_DAY));
        car.setCategory(carCategory);
        Transmission transmission = new Transmission();
        transmission.setId(resultSet.getInt(CAR_TRANSMISSION_ID));
        transmission.setName(resultSet.getString(CAR_TRANSMISSION_NAME));
        car.setTransmission(transmission);
        Body body = new Body();
        body.setId(resultSet.getInt(BODY_ID));
        body.setName(resultSet.getString(BODY_NAME));
        car.setBody(body);
        EngineType engineType = new EngineType();
        engineType.setId(resultSet.getInt(ENGINE_TYPE_ID));
        engineType.setName(resultSet.getString(ENGINE_TYPE_NAME));
        car.setEngineType(engineType);
        car.setHasAirConditioner(resultSet.getString(HAS_AC));
        car.setAvailable(resultSet.getString(IS_AVAILABLE));
        car.setEngineVolume(resultSet.getDouble(ENGINE_VOLUME));
        car.setBaggageAmount(resultSet.getInt(BAGGAGE_AMOUNT));
        car.setPassengerAmount(resultSet.getInt(PASSENGER_AMOUNT));
        car.setFuelConsumption(resultSet.getDouble(FUEL_CONSUMPTION));
        car.setDoorAmount(resultSet.getInt(DOOR_AMOUNT));
        String productionYear = resultSet.getString(PRODUCTION_YEAR);
        car.setProductionYear(productionYear.substring(YEAR_SUBSTRING_BEGIN_INDEX, YEAR_SUBSTRING_END_INDEX));
        car.setImageName(resultSet.getString(CAR_IMAGE));
        car.setMileage(resultSet.getInt(MILEAGE));
        return car;
    }

    @Override
    public Map<String, Integer> getCarDetails(Connection connection) throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_CAR_INFO_ID)) {
                while (resultSet.next()) {
                    map.put(resultSet.getString(BRAND_NAME), resultSet.getInt(BRAND_ID));
                    map.put(resultSet.getString(MODEL_NAME), resultSet.getInt(MODEL_ID));
                    map.put(resultSet.getString(COLOR_NAME), resultSet.getInt(COLOR_ID));
                    map.put(resultSet.getString(CAR_TRANSMISSION_NAME), resultSet.getInt(CAR_TRANSMISSION_ID));
                    map.put(resultSet.getString(BODY_NAME), resultSet.getInt(BODY_ID));
                    map.put(resultSet.getString(ENGINE_NAME), resultSet.getInt(ENGINE_TYPE_ID));
                    map.put(resultSet.getString(CAR_CATEGORY_NAME), resultSet.getInt(CAR_CATEGORY_ID));
                }
            }
        }
        return map;
    }

    @Override
    public void updateImage(Car car, Connection connection) throws SQLException {
        System.out.println("CarDAO.updateImage.");
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CAR_IMAGE)) {
            System.out.println("CarDAO.try");
            preparedStatement.setString(1, car.getImageName());
            preparedStatement.setLong(2, car.getId());
            preparedStatement.executeUpdate();
            System.out.println("CarDAO.ends");
        }
    }

    @Override
    public List<CarCategory> getCarCategory(Connection connection) throws SQLException {
        List<CarCategory> categoryList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_CATEGORY)) {
                while (resultSet.next()) {
                    CarCategory category = new CarCategory();
                    category.setId(resultSet.getInt(CAR_CATEGORY_ID));
                    category.setName(resultSet.getString(CAR_CATEGORY_DB_NAME));
                    category.setPricePerDay(resultSet.getDouble(CAR_PRICE_PER_DAY));
                    categoryList.add(category);
                }
            }
        }
        return categoryList;
    }

    @Override
    public void updatePrice(CarCategory category, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PRICE)) {
            preparedStatement.setDouble(1, category.getPricePerDay());
            preparedStatement.setLong(2, category.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public boolean deleteById(Long id, Connection connection) {
        return false;
    }

    @Override
    public Car getById(Long id, Connection connection) {
        return null;
    }
}