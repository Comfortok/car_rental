package com.epam.dao.impl;

import com.epam.dao.InvoiceDao;
import com.epam.entity.Invoice;
import com.epam.entity.PaymentType;
import com.epam.pool.ConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class InvoiceDaoImpl implements InvoiceDao {
    private static final String SQL_Q1 = "insert into car_rent.invoice(invoice_date, order_id, payment_type_id, total_amount, is_paid)\n" +
            "values(?, ?, ?, ?, ?)";

    @Override
    public void insert(Invoice invoice) throws SQLException, IOException {
        System.out.println("insert in InvoiceDAO");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        connection = connectionPool.getConnection();
        System.out.println("Connection is: " + connection);
        try {
            System.out.println("try starts...");
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_Q1);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parseDate = format.parse(String.valueOf(invoice.getDate()));
            Date sqlDate = new Date(parseDate.getTime());
            preparedStatement.setDate(1, invoice.getDate());
            preparedStatement.setLong(2, invoice.getOrder().getId());
            preparedStatement.setLong(3, invoice.getPaymentType().getId());
            preparedStatement.setDouble(4, invoice.getOrder().getPaymentSum());
            preparedStatement.setLong(5, invoice.getPaymentStatus().getId());
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
    public Invoice update(Invoice invoice) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        return false;
    }

    @Override
    public Invoice getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Invoice> getAll() throws SQLException, IOException {
        return null;
    }
}
