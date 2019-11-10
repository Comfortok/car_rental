package com.epam.action;

import com.epam.dao.impl.OrderDAO;
import com.epam.entity.Order;
import com.epam.entity.Status;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.action.ConstantField.CONFIRMED_ORDER_STATUS_ID;
import static com.epam.action.ConstantField.ORDER_ID;

public class ConfirmOrderAction implements IAction {
    private static final Logger LOG = Logger.getLogger(ConfirmOrderAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("ConfirmOrderAction execute starts.");
        OrderDAO orderDAO = new OrderDAO();
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        Order order = new Order();
        Status status = new Status();
        status.setId(CONFIRMED_ORDER_STATUS_ID);
        order.setId(orderId);
        order.setStatus(status);
        orderDAO.update(order);
        ShowAllAdminOrdersAction showAllAdminOrdersAction = new ShowAllAdminOrdersAction();
        return showAllAdminOrdersAction.execute(request, response);
    }
}