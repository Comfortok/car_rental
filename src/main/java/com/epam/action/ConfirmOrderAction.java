package com.epam.action;

import com.epam.constant.JspPagePath;
import com.epam.dao.impl.OrderDAO;
import com.epam.entity.Order;
import com.epam.entity.Status;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.SQLException;

import static com.epam.constant.ConstantField.*;

public class ConfirmOrderAction implements IAction {
    private static final Logger LOG = Logger.getLogger(ConfirmOrderAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        OrderDAO orderDAO = new OrderDAO();
        ShowAllAdminOrdersAction showAllAdminOrdersAction = new ShowAllAdminOrdersAction();
        String forward = showAllAdminOrdersAction.execute(request, response);
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            long orderId = Long.parseLong(request.getParameter(ORDER_ID));
            Order order = new Order();
            Status status = new Status();
            status.setId(CONFIRMED_ORDER_STATUS_ID);
            order.setId(orderId);
            order.setStatus(status);
            orderDAO.update(order, connection);
        } catch (SQLException e) {
            LOG.error("Exception in ConfirmOrderAction has happened. Can not update an order. ", e);
            return JspPagePath.ERROR_PAGE;
        } finally {
            connectionPool.freeConnection(connection);
        }
        return forward;
    }
}