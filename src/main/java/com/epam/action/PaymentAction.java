package com.epam.action;

import com.epam.dao.impl.InvoiceDaoImpl;
import com.epam.dao.impl.OrderDaoImpl;
import com.epam.entity.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        long orderId = Long.parseLong(request.getParameter("orderId"));
        double paymentSum = Double.parseDouble(request.getParameter("paymentSum"));
        System.out.println("orderSum: " + paymentSum);
        double actualSum = Double.parseDouble(request.getParameter("actualSum"));
        System.out.println("actualSum: " + actualSum);
        String page = null;
        if (paymentSum <= actualSum) {
            System.out.println("if in paymentAction");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();
            //java.sql.Date invoiceDate = (java.sql.Date) format.parse(String.valueOf(currentDate.getTime()));
            java.sql.Date invoiceDate = new java.sql.Date(currentDate.getDate());
            Invoice invoice = new Invoice();
            invoice.setDate(invoiceDate);
            Order order = new Order();
            order.setId(orderId);
            order.setPaymentSum(paymentSum);
            Status status = new Status();
            status.setId(3);
            order.setStatus(status);
            invoice.setOrder(order);
            PaymentType paymentType = new PaymentType();
            paymentType.setId(1);
            invoice.setPaymentType(paymentType);
            PaymentStatus paymentStatus = new PaymentStatus();
            paymentStatus.setId(1);
            invoice.setPaymentStatus(paymentStatus);
            InvoiceDaoImpl invoiceDaoImpl = new InvoiceDaoImpl();
            invoiceDaoImpl.insert(invoice);
            OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
            orderDaoImpl.update(order);
        }
        ShowUnpaidOrdersAction action = new ShowUnpaidOrdersAction();
        page = action.execute(request, response);
        return page;
    }
}
