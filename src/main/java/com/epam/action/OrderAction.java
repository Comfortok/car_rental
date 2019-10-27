package com.epam.action;

import com.epam.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("execute in OrderAction");
        long userId = (long) request.getSession().getAttribute("userId");
        System.out.println("userID: " + userId);
        long carId = Long.parseLong(request.getParameter("carId"));
        System.out.println("car_id: " + carId);
        double sumToPay;
        double carPrice = Double.parseDouble(request.getParameter("carPrice"));
        System.out.println("carPrice: " + carPrice);
        int period = Integer.parseInt(request.getParameter("period"));
        System.out.println("Period: " + period);
        sumToPay = carPrice * period;
        System.out.println("Sum: " + sumToPay);
        String carModel = request.getParameter("carModel");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedatePU = null;
        Date parsedateDO = null;
        java.sql.Date sqlDatePU = null;
        java.sql.Date sqlDateDO = null;
        try {
            System.out.println("try in OrderCA exe");
            parsedatePU = format.parse(request.getParameter("startDate"));
            System.out.println("parsePU: " + parsedatePU);
            parsedateDO = format.parse(request.getParameter("endDate"));
            System.out.println("parse: " + parsedateDO);
            sqlDatePU = new java.sql.Date(parsedatePU.getTime());
            sqlDateDO = new java.sql.Date(parsedateDO.getTime());
        } catch (ParseException e) {
            System.out.println("in catch...");
            e.printStackTrace();
        }
        request.setAttribute("userId", userId);
        request.setAttribute("carId", carId);
        request.setAttribute("carPrice", carPrice);
        request.setAttribute("payment", sumToPay);
        request.setAttribute("startDate", sqlDatePU);
        request.setAttribute("startEnd", sqlDateDO);
        request.setAttribute("carModel", carModel);
        return Path.ORDER_PAGE;
    }
}
