package com.epam.dao;

import com.epam.entity.*;
import com.epam.pool.ConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements DAO<Order> {
    private static final String SQL_QUERY = "SELECT * FROM car_rent.order";
    String SQL_Q = "INSERT INTO car_rent.order(user_id, car_id, status_id, start_date, end_date, pick_up_location_id, drop_off_location_id, payment_sum) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void insert(Order order) throws SQLException, IOException {
        System.out.println("insert in OrderDAO");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        connection = connectionPool.getConnection();
        System.out.println("Connection is: " + connection);
        try {
            System.out.println("try starts...");
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_Q);
            System.out.println("26 done");
            preparedStatement.setLong(1, order.getUserID());
            System.out.println("28 done");
            preparedStatement.setLong(2, order.getCarID());
            System.out.println("30 done");
            Status status = new Status();
            status.setId(1);
            preparedStatement.setInt(3, (int) status.getId());
            preparedStatement.setDate(4, (Date) order.getStartDate());
            preparedStatement.setDate(5, (Date) order.getEndDate());
            preparedStatement.setLong(6, 1);
            preparedStatement.setLong(7, 1);
            preparedStatement.setDouble(8, order.getPaymentSum());
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
        System.out.println("insert in oorderDAO ends...");
    }

    @Override
    public Order update(Order entity) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        return false;
    }

    @Override
    public Order getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Order> getAll() throws SQLException, IOException {
        System.out.println("getAll method starts...");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println("CP.getInstance()");
        Connection connection;
        connection = connectionPool.getConnection();
        System.out.println("C.getCon() " + connection);
        List<Order> orderList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_QUERY)) {
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

    private Order getOrderInfo(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("order_id"));
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        order.setUser(user);
        Car car = new Car();
        car.setId(resultSet.getLong("car_id"));
        order.setCar(car);
        Status status = new Status();
        status.setId(resultSet.getInt("status_id"));
        order.setStatus(status);
        order.setStartDate(resultSet.getDate("start_date"));
        order.setEndDate(resultSet.getDate("end_date"));
        Location pickUp = new Location();
        Location dropOff = new Location();
        pickUp.setId(resultSet.getInt("pick_up_location_id"));
        dropOff.setId(resultSet.getInt("drop_off_location_id"));
        order.setPickUp(pickUp);
        order.setDropOff(dropOff);
        order.setPaymentSum(resultSet.getDouble("payment_sum"));
        return order;
    }
}