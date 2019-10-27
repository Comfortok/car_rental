package com.epam.dao.impl;

import com.epam.dao.OrderDao;
import com.epam.entity.*;
import com.epam.entity.Driver;
import com.epam.pool.ConnectionPool;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private static final String SQL_QUERY1 = "SELECT car_rent.order.order_id, car_rent.order.user_id, car_rent.order.car_id, car_rent.order.status_id,\n" +
            "car_rent.order.start_date, car_rent.order.end_date, car_rent.order.payment_sum, \n" +
            "car_rent.brand.name AS brand_name, car_rent.model.name AS model_name, car_rent.status.description AS status_name\n" +
            "FROM car_rent.car\n" +
            "JOIN car_rent.order ON car_rent.order.car_id = car_rent.car.car_id\n" +
            "JOIN model ON car.model_id = model.model_id\n" +
            "JOIN brand ON brand.brand_id = model.brand_id\n" +
            "JOIN status ON car_rent.order.status_id = status.status_id";
    private static final String SQL_QUERY2 = "SELECT car_rent.order.order_id, car_rent.order.user_id, car_rent.order.car_id, car_rent.order.status_id,\n" +
            "car_rent.order.start_date, car_rent.order.end_date, car_rent.order.payment_sum,\n" +
            "car_rent.brand.name AS brand_name, car_rent.model.name AS model_name, car_rent.status.description AS status_name\n" +
            "FROM car\n" +
            "JOIN car_rent.order ON car_rent.order.car_id = car_rent.car.car_id\n" +
            "JOIN model ON car.model_id = model.model_id\n" +
            "JOIN brand ON brand.brand_id = model.brand_id\n" +
            "JOIN status ON car_rent.order.status_id = status.status_id \n" +
            "WHERE user_id = ?";
    private static final String SQL_QUERY3 = "SELECT * FROM car_rent.order WHERE user_id = ? AND status_id = ?";
    private static final String SQL_Q = "INSERT INTO car_rent.order(user_id, car_id, status_id, start_date, end_date, payment_sum) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_QUERY5 = "select order_driver.driver_id, driver.name, driver.surname, driver.date_of_birth, driver.phone_number,\n" +
            "passport.number AS passport_number, passport.date_of_issue AS passport_issue, \n" +
            "passport.date_of_expiry AS passport_expiry, passport.authority AS passport_authority, \n" +
            "driving_licence.number AS licence_number, driving_licence.date_of_issue AS licence_issue, \n" +
            "driving_licence.date_of_expiry AS licence_expiry, driving_licence.authority AS licence_authority, \n" +
            "driving_licence.category, car_rent.order.order_id, car_rent.order.user_id, car_rent.order.car_id, \n" +
            "car_rent.order.start_date, car_rent.order.end_date, car_rent.order.payment_sum, car_rent.brand.name AS brand_name, \n" +
            "car_rent.model.name AS model_name, car_rent.order.status_id, car_rent.status.description AS status_name\n" +
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
    private static final String SQL_QUERY6 = "select order_driver.driver_id, driver.name, driver.surname, driver.date_of_birth, driver.phone_number,\n" +
            "passport.number AS passport_number, passport.date_of_issue AS passport_issue, \n" +
            "passport.date_of_expiry AS passport_expiry, passport.authority AS passport_authority, \n" +
            "driving_licence.number AS licence_number, driving_licence.date_of_issue AS licence_issue, \n" +
            "driving_licence.date_of_expiry AS licence_expiry, driving_licence.authority AS licence_authority, \n" +
            "driving_licence.category, car_rent.order.order_id, car_rent.order.user_id, car_rent.order.car_id, \n" +
            "car_rent.order.start_date, car_rent.order.end_date, car_rent.order.payment_sum, car_rent.brand.name AS brand_name, \n" +
            "car_rent.model.name AS model_name, car_rent.order.status_id, car_rent.status.description AS status_name\n" +
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
    private static final String SQL_QUERY7 = "update car_rent.order set status_id = ? where order_id = ?";

    @Override
    public void insert(Order order) throws SQLException, IOException {//+
        System.out.println("insert in OrderDAO");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        connection = connectionPool.getConnection();
        System.out.println("Connection is: " + connection);
        try {
            System.out.println("try starts...");
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_Q);
            System.out.println("26 done");
            preparedStatement.setLong(1, order.getUser().getId());
            System.out.println("28 done");
            preparedStatement.setLong(2, order.getCar().getId());
            System.out.println("30 done");
            Status status = new Status();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parseStart = format.parse(String.valueOf(order.getStartDate()));
            java.util.Date parseEnd = format.parse(String.valueOf(order.getEndDate()));
            Date sqlStart = new Date(parseStart.getTime());
            Date sqlEnd = new Date(parseEnd.getTime());
            preparedStatement.setInt(3, (int) order.getStatus().getId());
            preparedStatement.setDate(4, sqlStart);
            preparedStatement.setDate(5, sqlEnd);
            preparedStatement.setDouble(6, order.getPaymentSum());
            System.out.println("Before exeUpdate");
            preparedStatement.executeUpdate();
            System.out.println("try ends...");
        } catch (SQLException | ParseException e) {
            System.out.println("catch...");
            e.printStackTrace();
        } finally {
            System.out.println("finally...");
            connectionPool.freeConnection(connection);
        }
        System.out.println("insert in oorderDAO ends...");
    }

    @Override
    public Order update(Order order) throws SQLException {
        System.out.println("Update in OrderDAO");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        connection = connectionPool.getConnection();
        System.out.println("Connection is: " + connection);
        try {
            System.out.println("try starts...");
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY7);
            preparedStatement.setLong(1, order.getStatus().getId());
            preparedStatement.setLong(2, order.getId());
            System.out.println("Before exeUpdate");
            preparedStatement.executeUpdate();
            System.out.println("try ends...");
        } catch (SQLException e) {
            System.out.println("catch...");
            e.printStackTrace();
        } finally {
            System.out.println("finally...");
            connectionPool.freeConnection(connection);
        }
        System.out.println("update in orderDAO ends...");
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        return false;
    }

    @Override
    public Order getById(Long id) throws SQLException {
        System.out.println("getById method starts...");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println("CP.getInstance()");
        Connection connection;
        connection = connectionPool.getConnection();
        System.out.println("C.getCon() " + connection);
        Order order = new Order();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY6)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    order = getOrderInfoForOperator(resultSet);
                }
            }
        } finally {
            System.out.println("finally... freeCon");
            connectionPool.freeConnection(connection);
        }
        return order;
    }

    @Override
    public List<Order> getAll() throws SQLException {//+
        System.out.println("getAll method starts...");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println("CP.getInstance()");
        Connection connection;
        connection = connectionPool.getConnection();
        System.out.println("C.getCon() " + connection);
        List<Order> orderList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_QUERY1)) {
                while (resultSet.next()) {
                    System.out.println("another while...");
                    orderList.add(getOrderInfo(resultSet));
                }
            }
        } finally {
            System.out.println("finally... freeCon");
            connectionPool.freeConnection(connection);
        }
        return orderList;
    }

    @Override
    public List<Order> getAllByUserId(Long id) throws SQLException, IOException {//+
        System.out.println("getAllByUserId method starts...");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        connection = connectionPool.getConnection();
        List<Order> orderList = new ArrayList<>();
        System.out.println("BEFORE TRY");
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY2)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println("IN TRY");
                while (resultSet.next()) {
                    System.out.println("IN WHILE");
                    orderList.add(getOrderInfo(resultSet));
                }
            }
        } finally {
            System.out.println("finally... freeCon");
            connectionPool.freeConnection(connection);
        }
        return orderList;
    }

    @Override
    public Order getOrderInfo(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("order_id"));
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        order.setUser(user);
        Car car = new Car();
        Brand brand = new Brand();
        Model model = new Model();
        brand.setName(resultSet.getString("brand_name"));
        model.setBrand(brand);
        model.setName(resultSet.getString("model_name"));
        car.setId(resultSet.getLong("car_id"));
        car.setModel(model);
        order.setCar(car);
        Status status = new Status();
        status.setId(resultSet.getInt("status_id"));
        status.setName(resultSet.getString("status_name"));
        order.setStatus(status);
        order.setStartDate(resultSet.getDate("start_date"));
        order.setEndDate(resultSet.getDate("end_date"));
        order.setPaymentSum(resultSet.getDouble("payment_sum"));
        return order;
    }

    @Override
    public Order getOrderByUserAndCar(Order order) throws SQLException {//+
        System.out.println("getByUserCar method starts in OrderDAO.");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT car_rent.order.order_id FROM car_rent.order WHERE user_id = ? and car_id = ? and status_id = ?")) {
            preparedStatement.setLong(1, order.getUser().getId());
            preparedStatement.setLong(2, order.getCar().getId());
            preparedStatement.setLong(3, order.getStatus().getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    order.setId(resultSet.getLong("order_id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            connection.close();
        }
        return order;
    }

    @Override
    public List<Order> getAllForOperator() throws SQLException, IOException {
        System.out.println("getAll method starts...");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println("CP.getInstance()");
        Connection connection;
        connection = connectionPool.getConnection();
        System.out.println("C.getCon() " + connection);
        List<Order> orderList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_QUERY5)) {
                while (resultSet.next()) {
                    System.out.println("another while...");
                    orderList.add(getOrderInfoForOperator(resultSet));
                }
            }
        } finally {
            System.out.println("finally... freeCon");
            connectionPool.freeConnection(connection);
        }
        return orderList;
    }

    public Order getOrderInfoForOperator(ResultSet resultSet) throws SQLException {
        Driver driver = new Driver();
        Passport passport = new Passport();
        DrivingLicence drivingLicence = new DrivingLicence();
        driver.setId(resultSet.getLong("driver_id"));
        driver.setName(resultSet.getString("name"));
        driver.setSurname(resultSet.getString("surname"));
        driver.setDateOfBirth(resultSet.getDate("date_of_birth"));
        driver.setPhoneNumber(resultSet.getString("phone_number"));
        passport.setNumber(resultSet.getString("passport_number"));
        passport.setDateOfIssue(resultSet.getDate("passport_issue"));
        passport.setDateOfExpiry(resultSet.getDate("passport_expiry"));
        passport.setAuthority(resultSet.getString("passport_authority"));
        driver.setPassport(passport);
        drivingLicence.setNumber(resultSet.getString("licence_number"));
        drivingLicence.setDateOfIssue(resultSet.getDate("licence_issue"));
        drivingLicence.setDateOfExpiry(resultSet.getDate("licence_expiry"));
        drivingLicence.setAuthority(resultSet.getString("licence_authority"));
        drivingLicence.setCategory(resultSet.getString("category"));
        driver.setDrivingLicence(drivingLicence);
        Order order = new Order();
        order.setId(resultSet.getLong("order_id"));
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        order.setUser(user);
        Car car = new Car();
        car.setId(resultSet.getLong("car_id"));
        order.setStartDate(resultSet.getDate("start_date"));
        order.setEndDate(resultSet.getDate("end_date"));
        order.setPaymentSum(resultSet.getDouble("payment_sum"));
        Brand brand = new Brand();
        Model model = new Model();
        brand.setName(resultSet.getString("brand_name"));
        model.setBrand(brand);
        model.setName(resultSet.getString("model_name"));
        car.setModel(model);
        order.setCar(car);
        Status status = new Status();
        status.setId(resultSet.getInt("status_id"));
        status.setName(resultSet.getString("status_name"));
        order.setStatus(status);
        order.setDriver(driver);
        return order;
    }
}