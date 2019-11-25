package com.epam.action;

import com.epam.constant.JspPagePath;
import com.epam.dao.impl.OrderDAO;
import com.epam.entity.Order;
import com.epam.entity.Status;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;

import static com.epam.constant.ConstantField.*;

public class DeclineOrderAction implements IAction {
    private static final Logger LOG = Logger.getLogger(DeclineOrderAction.class);
    private static final String USER_ROLE_ID = "1";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderDAO orderDAO = new OrderDAO();
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        HttpSession session = request.getSession();
        String forward;
        Order order = new Order();
        Status status = new Status();
        status.setId(DECLINED_ORDER_STATUS_ID);
        order.setId(orderId);
        order.setStatus(status);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            orderDAO.update(order, connection);
        } catch (Exception e) {
            LOG.error("Exception in DeclineOrderAction has happened. Can not update an order. ", e);
            return JspPagePath.ERROR_PAGE;
        } finally {
            connectionPool.freeConnection(connection);
        }
        String roleId = String.valueOf(session.getAttribute(USER_ROLE_ATTRIBUTE));
        if (roleId.equals(USER_ROLE_ID)) {
            ShowUserOrdersAction showUserOrdersAction = new ShowUserOrdersAction();
            forward = showUserOrdersAction.execute(request, response);
        } else {
            ShowAllAdminOrdersAction showAllAdminOrdersAction = new ShowAllAdminOrdersAction();
            forward = showAllAdminOrdersAction.execute(request, response);
        }
        return forward;
    }
}