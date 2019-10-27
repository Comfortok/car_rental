package com.epam.action;

import com.epam.dao.DriverDao;
import com.epam.dao.OrderDao;
import com.epam.dao.impl.DriverDaoImpl;
import com.epam.dao.impl.OrderDaoImpl;
import com.epam.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.Path.HOME_PAGE;

public class OrderCarAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("execute in OrderCA");
        long userId = (long) request.getSession().getAttribute("userId");
        System.out.println("userID: " + userId);
        long carId = Long.parseLong(request.getParameter("carId"));
        System.out.println("car_id: " + carId);
        double sumToPay = Double.parseDouble(request.getParameter("payment"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String passportNo = request.getParameter("passport");
        String passportAuthority = request.getParameter("passportAuthority");
        String licence = request.getParameter("licence");
        String licenceAuthority = request.getParameter("licenceAuthority");
        String category = request.getParameter("licenceCategory");

        Date parsedatePU;
        Date parsedateDO;
        Date birthDate = null;
        Date passportIssueDate = null;
        Date passportExpiryDate = null;
        Date licenceIssueDate = null;
        Date licenceExpiryDate = null;
        java.sql.Date sqlDatePU = null;
        java.sql.Date sqlDateDO = null;
        java.sql.Date sqlBirthDate = null;
        java.sql.Date sqlPassportIssueDate = null;
        java.sql.Date sqlPassportExpiryDate = null;
        java.sql.Date sqlLicenceIssueDate = null;
        java.sql.Date sqlLicenceExpiryDate = null;
        try {
            System.out.println("try in OrderCA exe");
            parsedatePU = format.parse(request.getParameter("startDate"));
            System.out.println("parsePU: " + parsedatePU);
            parsedateDO = format.parse(request.getParameter("endDate"));
            birthDate = format.parse(request.getParameter("birthDate"));
            passportIssueDate = format.parse(request.getParameter("passportIssue"));
            passportExpiryDate = format.parse(request.getParameter("passportExpiry"));
            licenceIssueDate = format.parse(request.getParameter("licenceIssue"));
            licenceExpiryDate = format.parse(request.getParameter("licenceExpiry"));

            sqlDatePU = new java.sql.Date(parsedatePU.getTime());
            System.out.println("sqlStart: " + sqlDatePU);
            sqlDateDO = new java.sql.Date(parsedateDO.getTime());
            sqlBirthDate = new java.sql.Date(birthDate.getTime());
            sqlPassportIssueDate = new java.sql.Date(passportIssueDate.getTime());
            sqlPassportExpiryDate = new java.sql.Date(passportExpiryDate.getTime());
            sqlLicenceIssueDate = new java.sql.Date(licenceIssueDate.getTime());
            sqlLicenceExpiryDate = new java.sql.Date(licenceExpiryDate.getTime());
        } catch (ParseException e) {
            System.out.println("in catch...");
            e.printStackTrace();
        }
        System.out.println("Dates after try: sqlStart: " + sqlDatePU + "; sqlEnd: " + sqlDateDO);
        Order order = new Order();
        User user = new User();
        user.setId(userId);
        order.setUser(user);
        Car car = new Car();
        car.setId(carId);
        order.setCar(car);
        Status status = new Status();
        status.setId(1);
        order.setStatus(status);
        order.setPaymentSum(sumToPay);
        order.setStartDate(sqlDatePU);
        order.setEndDate(sqlDateDO);
        System.out.println("Before creating orderDAO");
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        System.out.println("After creating orderDAO");
        orderDaoImpl.insert(order);
        System.out.println("After insering an order");

        DriverDaoImpl driverDaoImpl = new DriverDaoImpl();
        Driver driver = new Driver();
        driver.setName(name);
        driver.setSurname(surname);
        driver.setDateOfBirth(sqlBirthDate);
        driver.setPhoneNumber(phone);
        driverDaoImpl.insert(driver);

        Order nOrder = orderDaoImpl.getOrderByUserAndCar(order);
        System.out.println("nOrderID: " + nOrder.getId());
        Driver nDriver = driverDaoImpl.getDriverByPhone(driver);
        System.out.println("nDriverID: " + nDriver.getId());
        driverDaoImpl.insertOrderDriver(nDriver, nOrder);

        Passport passport = new Passport();
        passport.setNumber(passportNo);
        passport.setDateOfIssue(sqlPassportIssueDate);
        passport.setDateOfExpiry(sqlPassportExpiryDate);
        passport.setAuthority(passportAuthority);
        driver.setPassport(passport);
        DrivingLicence drivingLicence = new DrivingLicence();
        drivingLicence.setNumber(licence);
        drivingLicence.setDateOfIssue(sqlLicenceIssueDate);
        drivingLicence.setDateOfExpiry(sqlLicenceExpiryDate);
        drivingLicence.setAuthority(licenceAuthority);
        drivingLicence.setCategory(category);
        driver.setDrivingLicence(drivingLicence);
        driver.setId(nDriver.getId());
        driverDaoImpl.insertDriverInfo(driver);

        System.out.println("SUCCESS!");
        return HOME_PAGE;
    }
}