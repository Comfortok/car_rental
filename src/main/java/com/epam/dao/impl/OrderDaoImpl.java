package com.epam.dao.impl;

import com.epam.dao.OrderDao;
import com.epam.entity.*;
import com.epam.entity.Driver;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.epam.action.ConstantField.*;

public class OrderDaoImpl implements OrderDao {
    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final String SQL_SELECT_ALL_FROM_ORDER = "SELECT car_rent.order.order_id, car_rent.order.user_id, " +
            "car_rent.order.car_id, car_rent.order.status_id, car_rent.order.start_date, car_rent.order.end_date, " +
            "car_rent.order.payment_sum, car_rent.brand.name AS brand_name, car_rent.model.name AS model_name, " +
            "car_rent.status.description AS status_name\n" +
            "FROM car_rent.car\n" +
            "JOIN car_rent.order ON car_rent.order.car_id = car_rent.car.car_id\n" +
            "JOIN model ON car.model_id = model.model_id\n" +
            "JOIN brand ON brand.brand_id = model.brand_id\n" +
            "JOIN status ON car_rent.order.status_id = status.status_id";
    private final String SQL_SELECT_ORDERS_BY_USER_ID = "SELECT car_rent.order.order_id, car_rent.order.user_id, " +
            "car_rent.order.car_id, car_rent.order.status_id, car_rent.order.start_date, car_rent.order.end_date, " +
            "car_rent.order.payment_sum, car_rent.brand.name AS brand_name, car_rent.model.name AS model_name, " +
            "car_rent.status.description AS status_name\n" +
            "FROM car\n" +
            "JOIN car_rent.order ON car_rent.order.car_id = car_rent.car.car_id\n" +
            "JOIN model ON car.model_id = model.model_id\n" +
            "JOIN brand ON brand.brand_id = model.brand_id\n" +
            "JOIN status ON car_rent.order.status_id = status.status_id \n" +
            "WHERE user_id = ?";
    private final String SQL_SELECT_ORDER_BY_USER_CAR_STATUS = "SELECT car_rent.order.order_id " +
            "FROM car_rent.order WHERE user_id = ? and car_id = ? and status_id = ?";
    private final String SQL_INSERT_NEW_ORDER = "INSERT INTO car_rent.order(user_id, car_id, status_id, start_date, " +
            "end_date, payment_sum) VALUES(?, ?, ?, ?, ?, ?)";
    private final String SQL_SELECT_ORDER_FOR_OPERATOR = "select order_driver.driver_id, driver.name, driver.surname, " +
            "driver.date_of_birth, driver.phone_number, passport.number AS passport_number, " +
            "passport.date_of_issue AS passport_issue, passport.date_of_expiry AS passport_expiry, " +
            "passport.authority AS passport_authority, driving_licence.number AS licence_number, " +
            "driving_licence.date_of_issue AS licence_issue, driving_licence.date_of_expiry AS licence_expiry, " +
            "driving_licence.authority AS licence_authority, driving_licence.category, car_rent.order.order_id, " +
            "car_rent.order.user_id, car_rent.order.car_id, car_rent.order.start_date, car_rent.order.end_date, " +
            "car_rent.order.payment_sum, car_rent.brand.name AS brand_name, car_rent.model.name AS model_name, " +
            "car_rent.order.status_id, car_rent.status.description AS status_name\n" +
            "from car_rent.driver\n" +
            "inner join car_rent.order_driver on car_rent.order_driver.driver_id = driver.driver_id\n" +
            "inner join car_rent.order on car_rent.order.order_id = car_rent.order_driver.order_id\n" +
            "inner join car on car_rent.order.car_id = car_rent.car.car_id\n" +
            "inner join model on car.model_id = model.model_id\n" +
            "inner join brand on brand.brand_id = model.brand_id\n" +
            "inner join car_rent.status on car_rent.status.status_id = car_rent.order.status_id\n" +
            "inner join car_rent.passport on car_rent.passport.driver_id = driver.driver_id\n" +
            "inner join car_rent.driving_licence on car_rent.driving_licence.driver_id = driver.driver_id\n" +
            "order by car_rent.order.status_id";
    private static final String SQL_SELECT_ORDER_BY_ID = "select order_driver.driver_id, driver.name, driver.surname, " +
            "driver.date_of_birth, driver.phone_number, passport.number AS passport_number, " +
            "passport.date_of_issue AS passport_issue, passport.date_of_expiry AS passport_expiry, " +
            "passport.authority AS passport_authority, driving_licence.number AS licence_number, " +
            "driving_licence.date_of_issue AS licence_issue, driving_licence.date_of_expiry AS licence_expiry, " +
            "driving_licence.authority AS licence_authority, driving_licence.category, car_rent.order.order_id, " +
            "car_rent.order.user_id, car_rent.order.car_id, car_rent.order.start_date, car_rent.order.end_date, " +
            "car_rent.order.payment_sum, car_rent.brand.name AS brand_name, car_rent.model.name AS model_name, " +
            "car_rent.order.status_id, car_rent.status.description AS status_name\n" +
            "from car_rent.driver\n" +
            "inner join car_rent.order_driver on car_rent.order_driver.driver_id = driver.driver_id\n" +
            "inner join car_rent.order on car_rent.order.order_id = car_rent.order_driver.order_id\n" +
            "inner join car on car_rent.order.car_id = car_rent.car.car_id\n" +
            "inner join model on car.model_id = model.model_id\n" +
            "inner join brand on brand.brand_id = model.brand_id\n" +
            "inner join car_rent.status on car_rent.status.status_id = car_rent.order.status_id\n" +
            "inner join car_rent.passport on car_rent.passport.driver_id = driver.driver_id\n" +
            "inner join car_rent.driving_licence on car_rent.driving_licence.driver_id = driver.driver_id\n" +
            "where car_rent.order.order_id = ?";
    private final String SQL_UPDATE_ORDER_STATUS = "update car_rent.order set status_id = ? where order_id = ?";

    @Override
    public void insert(Order order) {
        LOG.info("OrderDaoImpl.insert()");
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_ORDER)) {
            preparedStatement.setLong(1, order.getUser().getId());
            preparedStatement.setLong(2, order.getCar().getId());
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            java.util.Date parseOrderStart = format.parse(String.valueOf(order.getStartDate()));
            java.util.Date parseOrderEnd = format.parse(String.valueOf(order.getEndDate()));
            Date sqlOrderStart = new Date(parseOrderStart.getTime());
            Date sqlOrderEnd = new Date(parseOrderEnd.getTime());
            preparedStatement.setInt(3, (int) order.getStatus().getId());
            preparedStatement.setDate(4, sqlOrderStart);
            preparedStatement.setDate(5, sqlOrderEnd);
            preparedStatement.setDouble(6, order.getPaymentSum());
            preparedStatement.executeUpdate();
        } catch (SQLException | ParseException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            connectionPool.freeConnection(connection);
        }
    }

    @Override
    public void update(Order order) {
        LOG.info("OrderDaoImpl.update()");
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER_STATUS)) {
            preparedStatement.setLong(1, order.getStatus().getId());
            preparedStatement.setLong(2, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            connectionPool.freeConnection(connection);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Order getById(Long id) {
        LOG.info("OrderDaoImpl.getById()");
        Connection connection = connectionPool.getConnection();
        Order order = new Order();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    order = getOrderInfoForOperator(resultSet);
                }
            }
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            connectionPool.freeConnection(connection);
        }
        return order;
    }

    @Override
    public List<Order> getAll() {
        LOG.info("OrderDaoImpl.getAll()");
        List<Order> orderList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_ORDER)) {
                while (resultSet.next()) {
                    orderList.add(getOrderInfo(resultSet));
                }
            }
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            connectionPool.freeConnection(connection);
        }
        return orderList;
    }

    @Override
    public List<Order> getAllByUserId(Long id) {
        LOG.info("OrderDaoImpl.getAllByUserId()");
        List<Order> orderList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDERS_BY_USER_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    orderList.add(getOrderInfo(resultSet));
                }
            }
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            connectionPool.freeConnection(connection);
        }
        return orderList;
    }

    @Override
    public Order getOrderInfo(ResultSet resultSet) {
        LOG.info("OrderDaoImpl.getOrderInfo()");
        Order order = new Order();
        try {
            order.setId(resultSet.getLong(RESULTSET_ORDER_ID));
            User user = new User();
            user.setId(resultSet.getLong(RESULTSET_USER_ID));
            order.setUser(user);
            Car car = new Car();
            Brand brand = new Brand();
            Model model = new Model();
            brand.setName(resultSet.getString(BRAND_NAME));
            model.setBrand(brand);
            model.setName(resultSet.getString(MODEL_NAME));
            car.setId(resultSet.getLong(RESULTSET_CAR_ID));
            car.setModel(model);
            order.setCar(car);
            Status status = new Status();
            status.setId(resultSet.getInt(RESULTSET_STATUS_ID));
            status.setName(resultSet.getString(RESULTSET_STATUS_NAME));
            order.setStatus(status);
            order.setStartDate(resultSet.getDate(RESULTSET_START_DATE));
            order.setEndDate(resultSet.getDate(RESULTSET_END_DATE));
            order.setPaymentSum(resultSet.getDouble(RESULTSET_PAYMENT_SUM));
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public Order getOrderByUserAndCar(Order order) {
        LOG.info("OrderDaoImpl.getOrderByUserAndCar()");
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                SQL_SELECT_ORDER_BY_USER_CAR_STATUS)) {
            preparedStatement.setLong(1, order.getUser().getId());
            preparedStatement.setLong(2, order.getCar().getId());
            preparedStatement.setLong(3, order.getStatus().getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    order.setId(resultSet.getLong(RESULTSET_ORDER_ID));
                }
            } catch (SQLException e) {
                LOG.error(e);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            connectionPool.freeConnection(connection);
        }
        return order;
    }

    @Override
    public List<Order> getAllForOperator() {
        LOG.info("OrderDaoImpl.getAllForOperator()");
        List<Order> orderList = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ORDER_FOR_OPERATOR)) {
                while (resultSet.next()) {
                    orderList.add(getOrderInfoForOperator(resultSet));
                }
            }
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            connectionPool.freeConnection(connection);
        }
        return orderList;
    }

    private Order getOrderInfoForOperator(ResultSet resultSet) {
        LOG.info("OrderDaoImpl.getOrderInfoForOperator()");
        Order order = new Order();
        try {
            Driver driver = new Driver();
            Passport passport = new Passport();
            DrivingLicence drivingLicence = new DrivingLicence();
            driver.setId(resultSet.getLong(DRIVER_ID));
            driver.setName(resultSet.getString(RESULTSET_DRIVER_NAME));
            driver.setSurname(resultSet.getString(RESULTSET_DRIVER_SURNAME));
            driver.setDateOfBirth(resultSet.getDate(RESULTSET_DRIVER_BIRTH_DATE));
            driver.setPhoneNumber(resultSet.getString(RESULTSET_DRIVER_PHONE_NUMBER));
            passport.setNumber(resultSet.getString(RESULTSET_DRIVER_PASSPORT_NUMBER));
            passport.setDateOfIssue(resultSet.getDate(RESULTSET_PASSPORT_ISSUE_DATE));
            passport.setDateOfExpiry(resultSet.getDate(RESULTSET_PASSPORT_EXPIRY_DATE));
            passport.setAuthority(resultSet.getString(RESULTSET_PASSPORT_AUTHORITY));
            driver.setPassport(passport);
            drivingLicence.setNumber(resultSet.getString(RESULTSET_LICENCE_NUMBER));
            drivingLicence.setDateOfIssue(resultSet.getDate(RESULTSET_LICENCE_ISSUE_DATE));
            drivingLicence.setDateOfExpiry(resultSet.getDate(RESULTSET_LICENCE_EXPIRY_DATE));
            drivingLicence.setAuthority(resultSet.getString(RESULTSET_LICENCE_AUTHORITY));
            drivingLicence.setCategory(resultSet.getString(RESULTSET_LICENCE_CATEGORY));
            driver.setDrivingLicence(drivingLicence);
            order.setId(resultSet.getLong(RESULTSET_ORDER_ID));
            User user = new User();
            user.setId(resultSet.getLong(RESULTSET_USER_ID));
            order.setUser(user);
            Car car = new Car();
            car.setId(resultSet.getLong(RESULTSET_CAR_ID));
            order.setStartDate(resultSet.getDate(RESULTSET_START_DATE));
            order.setEndDate(resultSet.getDate(RESULTSET_END_DATE));
            order.setPaymentSum(resultSet.getDouble(RESULTSET_PAYMENT_SUM));
            Brand brand = new Brand();
            Model model = new Model();
            brand.setName(resultSet.getString(BRAND_NAME));
            model.setBrand(brand);
            model.setName(resultSet.getString(MODEL_NAME));
            car.setModel(model);
            order.setCar(car);
            Status status = new Status();
            status.setId(resultSet.getInt(RESULTSET_STATUS_ID));
            status.setName(resultSet.getString(RESULTSET_STATUS_NAME));
            order.setStatus(status);
            order.setDriver(driver);
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        }
        return order;
    }
}