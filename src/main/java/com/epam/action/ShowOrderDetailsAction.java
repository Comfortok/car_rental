package com.epam.action;

import com.epam.constant.JspPagePath;
import com.epam.dao.impl.OrderDAO;
import com.epam.entity.Driver;
import com.epam.entity.DrivingLicence;
import com.epam.entity.Order;
import com.epam.entity.Passport;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.SQLException;

import static com.epam.constant.ConstantField.*;

public class ShowOrderDetailsAction implements IAction {
    private static final Logger LOG = Logger.getLogger(ShowOrderDetailsAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forward = JspPagePath.ADMIN_ORDER_DETAILS_PAGE;
        OrderDAO orderDAO = new OrderDAO();
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        String statusId = request.getParameter(STATUS_ID);
        ConnectionPool connectionPool = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            Order order = orderDAO.getById(orderId, connection);
            Driver driver = order.getDriver();
            Passport passport = driver.getPassport();
            DrivingLicence drivingLicence = driver.getDrivingLicence();
            setDriverAttributes(request, driver, passport, drivingLicence, orderId, statusId);
        } catch (SQLException e) {
            LOG.error("Exception in ShowOrderDetailsAction has happened. Can not get order from DB. ", e);
            return JspPagePath.ERROR_PAGE;
        } finally {
            connectionPool.freeConnection(connection);
        }
        return forward;
    }

    private void setDriverAttributes(HttpServletRequest request, Driver driver, Passport passport,
                                     DrivingLicence drivingLicence, long orderId, String statusId) {
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
    }
}