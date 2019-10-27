package com.epam.action;

import com.epam.Path;
import com.epam.dao.impl.OrderDaoImpl;
import com.epam.entity.Order;
import com.epam.entity.Status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeclineOrderAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("ConfrimOrder exe");
        OrderDaoImpl orderDaoImpl;
        orderDaoImpl = new OrderDaoImpl();
        long orderId = Long.parseLong(request.getParameter("orderId"));
        System.out.println("orderId: " + orderId);
        Order order = new Order();
        Status status = new Status();
        status.setId(5);
        order.setId(orderId);
        order.setStatus(status);
        orderDaoImpl.update(order);
        ShowAllOrdersAction showAllOrdersAction = new ShowAllOrdersAction();
        return showAllOrdersAction.execute(request, response);
    }
}
