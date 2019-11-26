package com.epam.dao.impl;

import com.epam.dao.IInvoiceDAO;
import com.epam.entity.Invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InvoiceDAO implements IInvoiceDAO {
    private static final String SQL_INSERT_NEW_INVOICE = "insert into car_rent.invoice(invoice_date, order_id, " +
            "payment_type_id, total_amount, is_paid) values(?, ?, ?, ?, ?)";

    @Override
    public void insert(Invoice invoice, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_INVOICE)) {
            preparedStatement.setDate(1, invoice.getDate());
            preparedStatement.setLong(2, invoice.getOrder().getId());
            preparedStatement.setLong(3, invoice.getPaymentType().getId());
            preparedStatement.setDouble(4, invoice.getOrder().getPaymentSum());
            preparedStatement.setLong(5, invoice.getPaymentStatus().getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(Invoice entity, Connection connection) {
    }

    @Override
    public boolean deleteById(Long id, Connection connection) {
        return false;
    }

    @Override
    public Invoice getById(Long id, Connection connection) {
        return null;
    }

    @Override
    public List<Invoice> getAll(Connection connection) {
        return null;
    }
}