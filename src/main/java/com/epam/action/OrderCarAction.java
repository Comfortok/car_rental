package com.epam.action;

import com.epam.Path;
import com.epam.dao.OrderDAO;
import com.epam.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.Path.AVAILABLE_CARS_PAGE;
import static com.epam.Path.HOME_PAGE;

public class OrderCarAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("execute in OrderCA");
        long userId = (long) request.getSession().getAttribute("userId");
        System.out.println("userID: " + userId);
        long carId = Long.parseLong(request.getParameter("carId"));
        System.out.println("car_id: " + carId);
        //long carId = (long) request.getSession().getAttribute("carId");
        //System.out.println("carId: " + carId);
        //long carid = Long.parseLong(String.valueOf(carId));
        double sumToPay;
        double carPrice = Double.parseDouble(request.getParameter("carPrice"));
        System.out.println("carPrice: " + carPrice);
        int period = Integer.parseInt(request.getParameter("period"));
        System.out.println("Period: " + period);
        sumToPay = carPrice * period;
        System.out.println("Sum: " + sumToPay);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedatePU = null;
        java.sql.Date sqlDate = null;
        try {
            System.out.println("try in OrderCA exe");
            parsedatePU = format.parse((String) request.getParameter("pickUpDate"));
            System.out.println("parsedate: " + parsedatePU);
            sqlDate = new java.sql.Date(parsedatePU.getTime());
        } catch (ParseException e) {
            System.out.println("in catch...");
            e.printStackTrace();
        }
        Order order = new Order();
        order.setUserID(userId);
        order.setCarID(carId);
        order.setPrice(carPrice);
        order.setPaymentSum(sumToPay);
        order.setStartDate(sqlDate);
        order.setEndDate(sqlDate);
        System.out.println("Before creating orderDAO");
        OrderDAO orderDAO = new OrderDAO();
        System.out.println("After creating orderDAO");
        orderDAO.insert(order);
        System.out.println("After insering an order");
        return HOME_PAGE;
    }
}