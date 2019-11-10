package com.epam.action;

import com.epam.Path;
import com.epam.dao.impl.OrderDaoImpl;
import com.epam.entity.Driver;
import com.epam.entity.DrivingLicence;
import com.epam.entity.Order;
import com.epam.entity.Passport;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.action.ConstantField.*;

public class ShowOrderDetailsAction implements Action {
    private static final Logger LOG = Logger.getLogger(ShowOrderDetailsAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("ShowOrderDetailsAction execute starts.");
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        String statusId = request.getParameter(STATUS_ID);
        Order order = orderDaoImpl.getById(orderId);
        Driver driver = order.getDriver();
        Passport passport = driver.getPassport();
        DrivingLicence drivingLicence = driver.getDrivingLicence();
        request.setAttribute(DRIVER_NAME, driver.getName());
        request.setAttribute(DRIVER_SURNAME, driver.getSurname());
        request.setAttribute(DRIVER_BIRTH_DATE, driver.getDateOfBirth());
        request.setAttribute(DRIVER_PHONE, driver.getPhoneNumber());
        request.setAttribute(PASSPORT_NUMBER, passport.getNumber());
        request.setAttribute(PASSPORT_ISSUE_DATE, passport.getDateOfIssue());
        request.setAttribute(PASSPORT_EXPIRY_DATE, passport.getDateOfExpiry());
        request.setAttribute(PASSPORT_AUTHORITY, passport.getAuthority());
        request.setAttribute(LICENCE_NUMBER, drivingLicence.getNumber());
        request.setAttribute(LICENCE_ISSUE_DATE, drivingLicence.getDateOfIssue());
        request.setAttribute(LICENCE_EXPIRY_DATE, drivingLicence.getDateOfExpiry());
        request.setAttribute(LICENCE_AUTHORITY, drivingLicence.getAuthority());
        request.setAttribute(LICENCE_CATEGORY, drivingLicence.getCategory());
        request.setAttribute(ORDER_ID, orderId);
        request.setAttribute(STATUS_ID, statusId);
        return Path.ADMIN_ORDER_DETAILS_PAGE;
    }
}