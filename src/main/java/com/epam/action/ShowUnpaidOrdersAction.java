package com.epam.action;

import com.epam.dao.OrderDAO;
import com.epam.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.Path.ORDERS;

public class ShowUnpaidOrdersAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("exe in ShowOrders");
        OrderDAO orderDAO;
        List<Order> orders;
        orderDAO = new OrderDAO();
        orders = orderDAO.getAll();
        List<Order> sortedList = new ArrayList<>();
        long userId = (long) request.getSession().getAttribute("userId");
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getUser().getId() != userId) {
            } else {
                sortedList.add(orders.get(i));
            }
        }
        request.setAttribute("orders", sortedList);
        System.out.println("orders: " + orders);
        System.out.println("sorted: " + sortedList);
        return ORDERS;
    }
}