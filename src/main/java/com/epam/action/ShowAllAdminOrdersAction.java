package com.epam.action;

import com.epam.Path;
import com.epam.dao.impl.OrderDAO;
import com.epam.entity.Order;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.action.ConstantField.ORDER_LIST;

public class ShowAllAdminOrdersAction implements IAction {
    private static final Logger LOG = Logger.getLogger(ShowAllAdminOrdersAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("ShowAllAdminOrdersAction execute starts.");
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.getAllForOperator();
        request.setAttribute(ORDER_LIST, orders);
        return Path.ADMIN_ORDERS_PAGE;
    }
}