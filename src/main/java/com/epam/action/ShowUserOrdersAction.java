package com.epam.action;

import com.epam.Path;
import com.epam.dao.impl.OrderDaoImpl;
import com.epam.entity.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import static com.epam.action.ConstantField.ORDER_LIST;
import static com.epam.action.ConstantField.USER_ID;

public class ShowUserOrdersAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        long userId = (long) request.getSession().getAttribute(USER_ID);
        List<Order> orders = orderDaoImpl.getAllByUserId(userId);
        request.setAttribute(ORDER_LIST, orders);
        return Path.ALL_ORDERS_PAGE;
    }
}