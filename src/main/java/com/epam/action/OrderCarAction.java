package com.epam.action;

import com.epam.constant.JspPagePath;
import com.epam.dao.impl.DriverDAO;
import com.epam.dao.impl.OrderDAO;
import com.epam.entity.*;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.constant.ConstantField.*;

public class OrderCarAction implements IAction {
    private static final Logger LOG = Logger.getLogger(OrderCarAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        LOG.debug("execute method starts in OrderCarAction.");
        String forward = JspPagePath.HOME_PAGE;
        long userId = (long) request.getSession().getAttribute(USER_ID);
        long carId = Long.parseLong(request.getParameter(CAR_ID));
        double sumToPay = Double.parseDouble(request.getParameter(ORDER_PAYMENT_SUM));
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        String name = request.getParameter(DRIVER_NAME);
        String surname = request.getParameter(DRIVER_SURNAME);
        String phone = request.getParameter(DRIVER_PHONE);
        String passportNumber = request.getParameter(PASSPORT_NUMBER);
        String passportAuthority = request.getParameter(PASSPORT_AUTHORITY);
        String licenceNumber = request.getParameter(LICENCE_NUMBER);
        String licenceAuthority = request.getParameter(LICENCE_AUTHORITY);
        String licenceCategory = request.getParameter(LICENCE_CATEGORY);
        setRequestAttribute(request, userId, carId, sumToPay);
        Date parseDateStart;
        Date parseDateEnd;
        Date birthDate;
        Date passportIssueDate;
        Date passportExpiryDate;
        Date licenceIssueDate;
        Date licenceExpiryDate;
        java.sql.Date sqlDateStart;
        java.sql.Date sqlDateEnd;
        java.sql.Date sqlBirthDate;
        java.sql.Date sqlPassportIssueDate;
        java.sql.Date sqlPassportExpiryDate;
        java.sql.Date sqlLicenceIssueDate;
        java.sql.Date sqlLicenceExpiryDate;
        try {
            parseDateStart = format.parse(request.getParameter(ORDER_START_DATE));
            parseDateEnd = format.parse(request.getParameter(ORDER_END_DATE));
            birthDate = format.parse(request.getParameter(DRIVER_BIRTH_DATE));
            passportIssueDate = format.parse(request.getParameter(PASSPORT_ISSUE_DATE));
            passportExpiryDate = format.parse(request.getParameter(PASSPORT_EXPIRY_DATE));
            licenceIssueDate = format.parse(request.getParameter(LICENCE_ISSUE_DATE));
            licenceExpiryDate = format.parse(request.getParameter(LICENCE_EXPIRY_DATE));
            sqlDateStart = new java.sql.Date(parseDateStart.getTime());
            sqlDateEnd = new java.sql.Date(parseDateEnd.getTime());
            sqlBirthDate = new java.sql.Date(birthDate.getTime());
            sqlPassportIssueDate = new java.sql.Date(passportIssueDate.getTime());
            sqlPassportExpiryDate = new java.sql.Date(passportExpiryDate.getTime());
            sqlLicenceIssueDate = new java.sql.Date(licenceIssueDate.getTime());
            sqlLicenceExpiryDate = new java.sql.Date(licenceExpiryDate.getTime());
        } catch (ParseException e) {
            LOG.error("Exception in OrderCarAction has happened. Can not parse dates. ", e);
            return JspPagePath.ERROR_PAGE;
        }
        if (name.isEmpty() || surname.isEmpty() || phone.isEmpty() || passportNumber.isEmpty() ||
                passportAuthority.isEmpty() || licenceNumber.isEmpty() || licenceAuthority.isEmpty() ||
                licenceCategory.isEmpty() || birthDate == null || parseDateStart == null || parseDateEnd == null) {
            request.setAttribute(EMPTY_FIELD_ERROR, EMPTY_FIELD_ERROR_MESSAGE);
            forward = JspPagePath.ORDER_FORM_PAGE;
        } else {
            ConnectionPool connectionPool = null;
            Connection connection = null;
            try {
                connectionPool = ConnectionPool.getInstance();
                connection = connectionPool.getConnection();
                connection.setAutoCommit(false);
                OrderDAO orderDAO = new OrderDAO();
                DriverDAO driverDAO = new DriverDAO();
                Order order = getOrderAttributes(userId, carId, sumToPay, sqlDateStart, sqlDateEnd);
                orderDAO.insert(order, connection);
                Driver driver = getDriverAttributes(name, surname, sqlBirthDate, phone);
                driverDAO.insert(driver, connection);
                long newDriverId = insertDataToDriverOrder(order, driver, driverDAO, orderDAO, connection);
                driver.setPassport(getDriverPassport(passportNumber, sqlPassportIssueDate,
                        sqlPassportExpiryDate, passportAuthority));
                driver.setDrivingLicence(getDriverLicence(licenceNumber, sqlLicenceIssueDate, sqlLicenceExpiryDate,
                        licenceAuthority, licenceCategory));
                driver.setId(newDriverId);
                driverDAO.insertDriverInfo(driver, connection);
                connection.commit();
                LOG.debug("execute method ends in OrderCarAction. Committing...");
            } catch (SQLException | ParseException e) {
                connection.rollback();
                LOG.error("Exception in OrderCarAction has happened. Can not insert all data to DB. Rolling back...", e);
                return JspPagePath.ERROR_PAGE;
            } finally {
                connectionPool.freeConnection(connection);
            }
        }
        return forward;
    }

    private void setRequestAttribute(HttpServletRequest request, long userId, long carId, double sumToPay) {
        request.setAttribute(USER_ID, userId);
        request.setAttribute(CAR_ID, carId);
        request.setAttribute(CAR_PRICE, request.getParameter(CAR_PRICE));
        request.setAttribute(ORDER_PAYMENT_SUM, sumToPay);
        request.setAttribute(ORDER_START_DATE, request.getParameter(ORDER_START_DATE));
        request.setAttribute(ORDER_END_DATE, request.getParameter(ORDER_END_DATE));
        request.setAttribute(CAR_MODEL, request.getParameter(CAR_MODEL));
    }

    private Order getOrderAttributes(long userId, long carId, double sumToPay, java.sql.Date sqlDateStart,
                                     java.sql.Date sqlDateEnd) {
        Order order = new Order();
        User user = new User();
        user.setId(userId);
        order.setUser(user);
        Car car = new Car();
        car.setId(carId);
        order.setCar(car);
        Status status = new Status();
        status.setId(FORMED_ORDER_STATUS_ID);
        order.setStatus(status);
        order.setPaymentSum(sumToPay);
        order.setStartDate(sqlDateStart);
        order.setEndDate(sqlDateEnd);
        return order;
    }

    private Driver getDriverAttributes(String name, String surname, java.sql.Date sqlBirthDate, String phone) {
        Driver driver = new Driver();
        driver.setName(name);
        driver.setSurname(surname);
        driver.setDateOfBirth(sqlBirthDate);
        driver.setPhoneNumber(phone);
        return driver;
    }

    private long insertDataToDriverOrder(Order order, Driver driver, DriverDAO driverDAO, OrderDAO orderDAO,
                                         Connection connection) throws SQLException {
        Order orderNumber = orderDAO.getOrderByUserAndCar(order, connection);
        Driver driverNumber = driverDAO.getDriverByPhone(driver, connection);
        driverDAO.insertOrderDriver(driverNumber, orderNumber, connection);
        return driverNumber.getId();
    }

    private Passport getDriverPassport(String passportNumber, java.sql.Date sqlPassportIssueDate,
                                       java.sql.Date sqlPassportExpiryDate, String passportAuthority) {
        Passport passport = new Passport();
        passport.setNumber(passportNumber);
        passport.setDateOfIssue(sqlPassportIssueDate);
        passport.setDateOfExpiry(sqlPassportExpiryDate);
        passport.setAuthority(passportAuthority);
        return passport;
    }

    private DrivingLicence getDriverLicence(String licenceNumber, java.sql.Date sqlLicenceIssueDate,
                                            java.sql.Date sqlLicenceExpiryDate, String licenceAuthority,
                                            String licenceCategory) {
        DrivingLicence drivingLicence = new DrivingLicence();
        drivingLicence.setNumber(licenceNumber);
        drivingLicence.setDateOfIssue(sqlLicenceIssueDate);
        drivingLicence.setDateOfExpiry(sqlLicenceExpiryDate);
        drivingLicence.setAuthority(licenceAuthority);
        drivingLicence.setCategory(licenceCategory);
        return drivingLicence;
    }
}