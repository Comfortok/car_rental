package com.epam.dao.impl;

import com.epam.dao.IDriverDAO;
import com.epam.entity.Driver;
import com.epam.entity.Order;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.epam.action.ConstantField.*;

public class DriverDAO implements IDriverDAO {
    private static final Logger LOG = Logger.getLogger(DriverDAO.class);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final String SQL_INSERT_DRIVER = "INSERT INTO car_rent.driver(name, surname, date_of_birth, phone_number)\n" +
            "VALUES(?, ?, ?, ?);";
    private final String SQL_INSERT_PASSPORT = "INSERT INTO car_rent.passport(number, date_of_issue, date_of_expiry, " +
            "authority, driver_id) VALUES(?, ?, ?, ?, ?);";
    private final String SQL_INSERT_LICENCE = "INSERT INTO car_rent.driving_licence(number, date_of_issue, " +
            "date_of_expiry, authority, category, driver_id) VALUES(?, ?, ?, ?, ?, ?);";
    private final String SQL_INSERT_ORDER_DRIVER = "INSERT INTO car_rent.order_driver(driver_id, order_id) VALUES(?, ?)";
    private final String SQL_SELECT_DRIVER = "SELECT driver_id FROM driver where phone_number = ?";
    private final String SQL_SELECT_ALL_FROM_ORDER_DRIVER = "SELECT * FROM order_driver where order_id = ?";

    @Override
    public void insert(Driver driver) {
        LOG.info("DriverDaoImpl.insert()");
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_DRIVER)) {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            java.util.Date parseBirthDate = format.parse(String.valueOf(driver.getDateOfBirth()));
            Date sqlBirthDate = new Date(parseBirthDate.getTime());
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setString(2, driver.getSurname());
            preparedStatement.setDate(3, sqlBirthDate);
            preparedStatement.setString(4, driver.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException | ParseException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            connectionPool.freeConnection(connection);
        }
    }

    @Override
    public void update(Driver entity) {
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Driver getById(Long id) {
        LOG.info("DriverDaoImpl.getById()");
        Connection connection = connectionPool.getConnection();
        Driver driver = new Driver();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_FROM_ORDER_DRIVER)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    driver.setId(resultSet.getLong(DRIVER_ID));
                }
            }
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            connectionPool.freeConnection(connection);
        }
        return driver;
    }

    @Override
    public List<Driver> getAll() {
        return null;
    }

    @Override
    public void insertOrderDriver(Driver driver, Order order) {
        LOG.info("DriverDaoImpl.insertOrderDriver()");
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER_DRIVER)) {
            preparedStatement.setLong(1, driver.getId());
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
    public void insertDriverInfo(Driver driver) {
        LOG.info("DriverDaoImpl.insertDriverInfo()");
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PASSPORT);
             PreparedStatement preparedStatementSecond = connection.prepareStatement(SQL_INSERT_LICENCE)) {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            java.util.Date parsePassportIssue = format.parse(String.valueOf(driver.getPassport().getDateOfIssue()));
            java.util.Date parsePassportExpiry = format.parse(String.valueOf(driver.getPassport().getDateOfExpiry()));
            java.util.Date parseLicenceIssue = format.parse(String.valueOf(driver.getDrivingLicence().getDateOfIssue()));
            java.util.Date parseLicenceExpiry = format.parse(String.valueOf(driver.getDrivingLicence().getDateOfExpiry()));
            Date sqlPassportIssue = new Date(parsePassportIssue.getTime());
            Date sqlPassportExpiry = new Date(parsePassportExpiry.getTime());
            Date sqlLicenceIssue = new Date(parseLicenceIssue.getTime());
            Date sqlLicenceExpiry = new Date(parseLicenceExpiry.getTime());
            preparedStatement.setString(1, driver.getPassport().getNumber());
            preparedStatement.setDate(2, sqlPassportIssue);
            preparedStatement.setDate(3, sqlPassportExpiry);
            preparedStatement.setString(4, driver.getPassport().getAuthority());
            preparedStatement.setLong(5, driver.getId());
            preparedStatement.executeUpdate();
            preparedStatementSecond.setString(1, driver.getDrivingLicence().getNumber());
            preparedStatementSecond.setDate(2, sqlLicenceIssue);
            preparedStatementSecond.setDate(3, sqlLicenceExpiry);
            preparedStatementSecond.setString(4, driver.getDrivingLicence().getAuthority());
            preparedStatementSecond.setString(5, driver.getDrivingLicence().getCategory());
            preparedStatementSecond.setLong(6, driver.getId());
            preparedStatementSecond.executeUpdate();
        } catch (SQLException | ParseException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            connectionPool.freeConnection(connection);
        }
    }

    @Override
    public Driver getDriverByPhone(Driver driver) {
        LOG.info("DriverDaoImpl.getDriverByPhone()");
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_DRIVER)) {
            preparedStatement.setString(1, driver.getPhoneNumber());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    driver.setId(resultSet.getLong(DRIVER_ID));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            connectionPool.freeConnection(connection);
        }
        return driver;
    }
}