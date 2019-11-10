package com.epam.action;

import com.epam.dao.impl.OrderDAO;
import com.epam.entity.Order;
import com.epam.entity.Status;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.action.ConstantField.*;

public class DeclineOrderAction implements IAction {
    private static final Logger LOG = Logger.getLogger(DeclineOrderAction.class);
    private static final String USER_ROLE_ID = "1";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("DeclineOrderAction execute starts.");
        OrderDAO orderDAO = new OrderDAO();
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        HttpSession session = request.getSession();
        String forward = "";
        Order order = new Order();
        Status status = new Status();
        status.setId(DECLINED_ORDER_STATUS_ID);
        order.setId(orderId);
        order.setStatus(status);
        orderDAO.update(order);
        if (session.getAttribute(USER_ROLE_ATTRIBUTE).equals(USER_ROLE_ID)) {
            ShowUserOrdersAction showUserOrdersAction = new ShowUserOrdersAction();
            forward = showUserOrdersAction.execute(request, response);
        } else {
            ShowAllAdminOrdersAction showAllAdminOrdersAction = new ShowAllAdminOrdersAction();
            forward = showAllAdminOrdersAction.execute(request, response);
        }
        return forward;
    }
}