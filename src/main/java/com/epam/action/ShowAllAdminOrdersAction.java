package com.epam.action;

import com.epam.constant.JspPagePath;
import com.epam.dao.impl.OrderDAO;
import com.epam.entity.Order;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.List;

import static com.epam.constant.ConstantField.ORDER_LIST;

public class ShowAllAdminOrdersAction implements IAction {
    private static final Logger LOG = Logger.getLogger(ShowAllAdminOrdersAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders;
        String forward = JspPagePath.ADMIN_ORDERS_PAGE;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            orders = orderDAO.getAllForOperator(connection);
            request.setAttribute(ORDER_LIST, orders);
        } catch (Exception e) {
            LOG.error("Exception in ShowAllAdminOrdersAction has happened. Can not get orders from DB. ", e);
            return JspPagePath.ERROR_PAGE;
        } finally {
            connectionPool.freeConnection(connection);
        }
        return forward;
    }
}