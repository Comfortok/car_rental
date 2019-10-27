package com.epam.dao.impl;

import com.epam.dao.DriverDao;
import com.epam.entity.Driver;
import com.epam.entity.Order;
import com.epam.pool.ConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class DriverDaoImpl implements DriverDao {
    private static final String SQL_Q1 = "INSERT INTO car_rent.driver(name, surname, date_of_birth, phone_number)\n" +
            "VALUES(?, ?, ?, ?);";
    private static final String SQL_Q2 = "INSERT INTO car_rent.passport(number, date_of_issue, date_of_expiry, authority, driver_id)\n" +
            "VALUES(?, ?, ?, ?, ?);";
    private static final String SQL_Q3 = "INSERT INTO car_rent.driving_licence(number, date_of_issue, date_of_expiry, authority, category, driver_id)\n" +
            "VALUES(?, ?, ?, ?, ?, ?);";
    private static final String SQL_Q4 = "INSERT INTO car_rent.order_driver(driver_id, order_id) VALUES(?, ?)";

    @Override
    public void insert(Driver driver) throws SQLException, IOException {
        System.out.println("insert in DriverDAO");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        connection = connectionPool.getConnection();
        System.out.println("Connection is: " + connection);
        try {
            System.out.println("try starts...");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parseBirthDate = format.parse(String.valueOf(driver.getDateOfBirth()));
            Date sqlBirthDate = new Date(parseBirthDate.getTime());
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_Q1);
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setString(2, driver.getSurname());
            preparedStatement.setDate(3, sqlBirthDate);
            preparedStatement.setString(4, driver.getPhoneNumber());
            preparedStatement.executeUpdate();
            System.out.println("try ends...");
        } catch (SQLException | ParseException e) {
            System.out.println("catch...");
            e.printStackTrace();
        } finally {
            System.out.println("finally...");
            connectionPool.freeConnection(connection);
        }
        System.out.println("insert in driverDAO ends...");
    }

    @Override
    public Driver update(Driver entity) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        return false;
    }

    @Override
    public Driver getById(Long id) throws SQLException {
        System.out.println("getById method starts in DriverDAO.");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        connection = connectionPool.getConnection();
        Driver driver = new Driver();
        System.out.println("connection: " + connection.getClientInfo());
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM order_driver where order_id = ?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    driver.setId(resultSet.getLong("driver_id"));
                }
            }
        } finally {
            connection.close();
        }
        return driver;
    }

    @Override
    public List<Driver> getAll() throws SQLException, IOException {
        return null;
    }

/*    private Driver getDriverInfo(ResultSet resultSet) throws SQLException {
        System.out.println("getDriverInfo starts");
        Driver driver = new Driver();
        driver.setId(resultSet.getLong("driver_id"));
        System.out.println("getDriverInfo ends");
        return driver;
    }*/

    @Override
    public void insertOrderDriver(Driver driver, Order order) {
        System.out.println("insertOrderDriver in DriverDAO");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        connection = connectionPool.getConnection();
        System.out.println("Connection is: " + connection);
        try {
            System.out.println("try starts...");
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_Q4);
            preparedStatement.setLong(1, driver.getId());
            preparedStatement.setLong(2, order.getId());
            preparedStatement.executeUpdate();
            System.out.println("try ends...");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally...");
            connectionPool.freeConnection(connection);
        }
        System.out.println("insertOrderDriver ends...");
    }

    @Override
    public void insertDriverInfo(Driver driver) {
        System.out.println("insertDriverInfo in DriverDAO");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        connection = connectionPool.getConnection();
        System.out.println("Connection is: " + connection);
        try {
            System.out.println("try starts...");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsePassportIssue = format.parse(String.valueOf(driver.getPassport().getDateOfIssue()));
            java.util.Date parsePassportExpiry = format.parse(String.valueOf(driver.getPassport().getDateOfExpiry()));
            java.util.Date parseLicenceIssue = format.parse(String.valueOf(driver.getDrivingLicence().getDateOfIssue()));
            java.util.Date parseLicenceExpiry = format.parse(String.valueOf(driver.getDrivingLicence().getDateOfExpiry()));
            Date sqlPassportIssue = new Date(parsePassportIssue.getTime());
            Date sqlPassportExpiry = new Date(parsePassportExpiry.getTime());
            Date sqlLicenceIssue = new Date(parseLicenceIssue.getTime());
            Date sqlLicenceExpiry = new Date(parseLicenceExpiry.getTime());

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_Q2);
            preparedStatement.setString(1, driver.getPassport().getNumber());
            preparedStatement.setDate(2, sqlPassportIssue);
            preparedStatement.setDate(3, sqlPassportExpiry);
            preparedStatement.setString(4, driver.getPassport().getAuthority());
            preparedStatement.setLong(5, driver.getId());
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement2 = connection.prepareStatement(SQL_Q3);
            preparedStatement2.setString(1, driver.getDrivingLicence().getNumber());
            preparedStatement2.setDate(2, sqlLicenceIssue);
            preparedStatement2.setDate(3, sqlLicenceExpiry);
            preparedStatement2.setString(4, driver.getDrivingLicence().getAuthority());
            preparedStatement2.setString(5, driver.getDrivingLicence().getCategory());
            preparedStatement2.setLong(6, driver.getId());
            preparedStatement2.executeUpdate();
            System.out.println("try ends...");
        } catch (SQLException | ParseException e) {
            System.out.println("catch...");
            e.printStackTrace();
        } finally {
            System.out.println("finally...");
            connectionPool.freeConnection(connection);
        }
        System.out.println("insert in driverDAO ends...");
    }

    @Override
    public Driver getDriverByPhone(Driver driver) throws SQLException {
        System.out.println("getByPhone method starts in DriverDAO.");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT driver_id FROM driver where phone_number = ?")) {
            preparedStatement.setString(1, driver.getPhoneNumber());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    driver.setId(resultSet.getLong("driver_id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            connection.close();
        }
        return driver;
    }
}