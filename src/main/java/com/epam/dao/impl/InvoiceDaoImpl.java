package com.epam.dao.impl;

import com.epam.dao.InvoiceDao;
import com.epam.entity.Invoice;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InvoiceDaoImpl implements InvoiceDao {
    private static final Logger LOG = Logger.getLogger(InvoiceDaoImpl.class);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String SQL_INSERT_NEW_INVOICE = "insert into car_rent.invoice(invoice_date, order_id, " +
            "payment_type_id, total_amount, is_paid) values(?, ?, ?, ?, ?)";

    @Override
    public void insert(Invoice invoice) {
        LOG.info("InvoiceDaoImpl.insert()");
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_INVOICE)) {
            preparedStatement.setDate(1, invoice.getDate());
            preparedStatement.setLong(2, invoice.getOrder().getId());
            preparedStatement.setLong(3, invoice.getPaymentType().getId());
            preparedStatement.setDouble(4, invoice.getOrder().getPaymentSum());
            preparedStatement.setLong(5, invoice.getPaymentStatus().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
            e.printStackTrace();
        }
    }

    @Override
    public void update(Invoice invoice) {
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Invoice getById(Long id) {
        return null;
    }

    @Override
    public List<Invoice> getAll() {
        return null;
    }
}