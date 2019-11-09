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

public class ShowAllAdminOrdersAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        List<Order> orders = orderDaoImpl.getAllForOperator();
        request.setAttribute(ORDER_LIST, orders);
        return Path.ADMIN_ORDERS_PAGE;
    }
}