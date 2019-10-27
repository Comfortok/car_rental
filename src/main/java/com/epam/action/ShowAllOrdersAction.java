package com.epam.action;

import com.epam.Path;
import com.epam.dao.impl.OrderDaoImpl;
import com.epam.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowAllOrdersAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("exe in ShowOrders");
        OrderDaoImpl orderDaoImpl;
        List<Order> orders;
        orderDaoImpl = new OrderDaoImpl();
        orders = orderDaoImpl.getAllForOperator();
        request.setAttribute("orders", orders);
        System.out.println(orders);
        return Path.ADMIN_ORDERS_PAGE;
    }
}
