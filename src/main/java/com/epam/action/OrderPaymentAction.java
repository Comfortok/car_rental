package com.epam.action;

import com.epam.dao.impl.InvoiceDaoImpl;
import com.epam.dao.impl.OrderDaoImpl;
import com.epam.entity.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static com.epam.action.ConstantField.*;

public class OrderPaymentAction implements Action {
    private static final Logger LOG = Logger.getLogger(OrderPaymentAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("OrderPaymentAction execute starts.");
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        double paymentSum = Double.parseDouble(request.getParameter(PAYMENT_SUM));
        double actualSum = Double.parseDouble(request.getParameter(ACTUAL_SUM));
        if (paymentSum == actualSum) {
            Date currentDate = new Date();
            java.sql.Date invoiceDate = new java.sql.Date(currentDate.getDate());
            Invoice invoice = new Invoice();
            invoice.setDate(invoiceDate);
            Order order = new Order();
            order.setId(orderId);
            order.setPaymentSum(paymentSum);
            Status status = new Status();
            status.setId(PAID_ORDER_STATUS_ID);
            order.setStatus(status);
            invoice.setOrder(order);
            PaymentType paymentType = new PaymentType();
            paymentType.setId(PAYMENT_BANK_CARD_ID);
            invoice.setPaymentType(paymentType);
            PaymentStatus paymentStatus = new PaymentStatus();
            paymentStatus.setId(PAYMENT_PAID_ID);
            invoice.setPaymentStatus(paymentStatus);
            InvoiceDaoImpl invoiceDaoImpl = new InvoiceDaoImpl();
            invoiceDaoImpl.insert(invoice);
            OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
            orderDaoImpl.update(order);
        } else {
            request.setAttribute(WRONG_SUM_ERROR, WRONG_SUM_ERROR_MESSAGE);
        }
        ShowUserOrdersAction action = new ShowUserOrdersAction();
        return action.execute(request, response);
    }
}