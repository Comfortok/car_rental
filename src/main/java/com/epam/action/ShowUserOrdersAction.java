package com.epam.action;

import com.epam.Path;
import com.epam.dao.impl.OrderDAO;
import com.epam.entity.Order;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.action.ConstantField.ORDER_LIST;
import static com.epam.action.ConstantField.USER_ID;

public class ShowUserOrdersAction implements IAction {
    private static final Logger LOG = Logger.getLogger(ShowUserOrdersAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("ShowUserOrdersAction execute starts.");
        OrderDAO orderDAO = new OrderDAO();
        long userId = (long) request.getSession().getAttribute(USER_ID);
        List<Order> orders = orderDAO.getAllByUserId(userId);
        request.setAttribute(ORDER_LIST, orders);
        return Path.ALL_ORDERS_PAGE;
    }
}