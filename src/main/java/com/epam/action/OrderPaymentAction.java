package com.epam.action;

import com.epam.constant.JspPagePath;
import com.epam.dao.impl.InvoiceDAO;
import com.epam.dao.impl.OrderDAO;
import com.epam.entity.*;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import static com.epam.constant.ConstantField.*;

public class OrderPaymentAction implements IAction {
    private static final Logger LOG = Logger.getLogger(DeclineOrderAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        LOG.debug("execute method starts in OrderPaymentAction.");
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        double paymentSum = Double.parseDouble(request.getParameter(PAYMENT_SUM));
        String stringActualSum = request.getParameter(ACTUAL_SUM);
        double actualSum = Double.parseDouble(stringActualSum);
        ShowUserOrdersAction showUserOrdersAction = new ShowUserOrdersAction();
        String forward = showUserOrdersAction.execute(request, response);
        if ((paymentSum == actualSum) && (!stringActualSum.isEmpty())) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
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
            InvoiceDAO invoiceDAO = new InvoiceDAO();
            OrderDAO orderDAO = new OrderDAO();
            try {
                invoiceDAO.insert(invoice, connection);
                orderDAO.update(order, connection);
                connection.commit();
                LOG.debug("execute method ends in OrderPaymentAction. Committing...");
            } catch (Exception e) {
                connection.rollback();
                LOG.error("Exception in OrderFormAction has happened. Can not insert invoice or update order. " +
                        "Rolling back...", e);
                return JspPagePath.ERROR_PAGE;
            } finally {
                connectionPool.freeConnection(connection);
            }
        } else {
            request.setAttribute(WRONG_SUM_ERROR, WRONG_SUM_ERROR_MESSAGE);
            OrderPaymentFormAction orderPaymentFormAction = new OrderPaymentFormAction();
            forward = orderPaymentFormAction.execute(request, response);
        }
        return forward;
    }
}