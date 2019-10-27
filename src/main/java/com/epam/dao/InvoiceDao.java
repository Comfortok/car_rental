package com.epam.dao;

import com.epam.entity.Invoice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface InvoiceDao extends Dao<Invoice> {
    @Override
    void insert(Invoice invoice) throws SQLException, IOException;

    @Override
    Invoice update(Invoice invoice) throws SQLException;

    @Override
    boolean deleteById(Long id) throws SQLException;

    @Override
    Invoice getById(Long id) throws SQLException;

    @Override
    List<Invoice> getAll() throws SQLException, IOException;
}