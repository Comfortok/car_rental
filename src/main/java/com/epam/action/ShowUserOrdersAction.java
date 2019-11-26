package com.epam.action;

import com.epam.constant.JspPagePath;
import com.epam.dao.impl.OrderDAO;
import com.epam.entity.Order;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.epam.constant.ConstantField.*;

public class ShowUserOrdersAction implements IAction {
    private static final Logger LOG = Logger.getLogger(ShowUserOrdersAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("ShowUserOrdersAction execute starts.");
        String forward = JspPagePath.ALL_ORDERS_PAGE;
        OrderDAO orderDAO = new OrderDAO();
        long userId = (long) request.getSession().getAttribute(USER_ID);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            List<Order> orders = orderDAO.getAllByUserId(userId, connection);
            request.setAttribute(ORDER_LIST, orders);
        } catch (SQLException e) {
            LOG.error("Exception in ShowUserOrdersAction has happened. Can not get orders from DB. ", e);
            return JspPagePath.ERROR_PAGE;
        } finally {
            connectionPool.freeConnection(connection);
        }
        return forward;
    }
}