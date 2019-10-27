package com.epam.action;

import com.epam.Path;
import com.epam.dao.OrderDao;
import com.epam.dao.impl.OrderDaoImpl;
import com.epam.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.Path.ORDERS;

public class UserOrderAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("exe in UserOrderAction");
        OrderDaoImpl orderDaoImpl;
        List<Order> orders;
        long userId = (long) request.getSession().getAttribute("userId");
        orderDaoImpl = new OrderDaoImpl();//+
        orders = orderDaoImpl.getAllByUserId(userId);
        request.setAttribute("orders", orders);
        System.out.println("Orders: " + orders);
        return ORDERS;
    }
}