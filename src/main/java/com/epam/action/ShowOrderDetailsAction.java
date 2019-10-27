package com.epam.action;

import com.epam.Path;
import com.epam.dao.impl.OrderDaoImpl;
import com.epam.entity.Driver;
import com.epam.entity.DrivingLicence;
import com.epam.entity.Order;
import com.epam.entity.Passport;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowOrderDetailsAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("ShowOrderDetails exe");
        OrderDaoImpl orderDaoImpl;
        orderDaoImpl = new OrderDaoImpl();
        long orderId = Long.parseLong(request.getParameter("orderId"));
        Order order = orderDaoImpl.getById(orderId);
        Driver driver = order.getDriver();
        Passport passport = driver.getPassport();
        DrivingLicence drivingLicence = driver.getDrivingLicence();
        request.setAttribute("driverName", driver.getName());
        request.setAttribute("driverSurname", driver.getSurname());
        request.setAttribute("driverBirth", driver.getDateOfBirth());
        request.setAttribute("driverPhone", driver.getPhoneNumber());
        request.setAttribute("passportNumber", passport.getNumber());
        request.setAttribute("passportIssue", passport.getDateOfIssue());
        request.setAttribute("passportExpiry", passport.getDateOfExpiry());
        request.setAttribute("passportAuthority", passport.getAuthority());
        request.setAttribute("licenceNumber", drivingLicence.getNumber());
        request.setAttribute("licenceIssue", drivingLicence.getDateOfIssue());
        request.setAttribute("licenceExpiry", drivingLicence.getDateOfExpiry());
        request.setAttribute("licenceAuthority", drivingLicence.getAuthority());
        request.setAttribute("licenceCategory", drivingLicence.getCategory());
        return Path.ADMIN_ORDERS_DETAILS_PAGE;
    }
}
