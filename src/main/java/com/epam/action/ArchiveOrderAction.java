package com.epam.action;

import com.epam.dao.impl.OrderDaoImpl;
import com.epam.entity.Order;
import com.epam.entity.Status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.epam.action.ConstantField.*;

public class ArchiveOrderAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        Order order = new Order();
        Status status = new Status();
        status.setId(ARCHIVED_ORDER_STATUS_ID);
        order.setId(orderId);
        order.setStatus(status);
        orderDaoImpl.update(order);
        ShowAllAdminOrdersAction showAllAdminOrdersAction = new ShowAllAdminOrdersAction();
        return showAllAdminOrdersAction.execute(request, response);
    }
}