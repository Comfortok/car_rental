package com.epam.dao;

import com.epam.entity.Invoice;

import java.util.List;

public interface InvoiceDao extends Dao<Invoice> {
    @Override
    void insert(Invoice invoice);

    @Override
    void update(Invoice invoice);

    @Override
    boolean deleteById(Long id);

    @Override
    Invoice getById(Long id);

    @Override
    List<Invoice> getAll();
}