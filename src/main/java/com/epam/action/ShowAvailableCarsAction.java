package com.epam.action;

import com.epam.dao.impl.CarDAO;
import com.epam.entity.Car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.epam.constant.JspPagePath;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import static com.epam.constant.ConstantField.*;

public class ShowAvailableCarsAction implements IAction {
    private static final Logger LOG = Logger.getLogger(ShowAvailableCarsAction.class);
    private static final int MIN_PERIOD = 1;
    private static final int COUNT_ZERO = 0;
    private static final int COUNT_ONE = 1;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forward = JspPagePath.ALL_CARS_PAGE;
        CarDAO carDAO = new CarDAO();
        List<Car> availableCars = new ArrayList<>();
        List<Car> allCars;
        List<Car> allCarsInOrder;
        java.sql.Date startDate;
        java.sql.Date endDate;
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date userStartDate;
        Date userEndDate;
        ConnectionPool connectionPool = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            allCars = carDAO.getAll(connection);
            allCarsInOrder = carDAO.getAvailable(connection);
            try {
                userStartDate = format.parse(request.getParameter(ORDER_START_DATE));
                userEndDate = format.parse(request.getParameter(ORDER_END_DATE));
                startDate = new java.sql.Date(userStartDate.getTime());
                endDate = new java.sql.Date(userEndDate.getTime());
            } catch (ParseException e) {
                LOG.error("Exception in ShowAvailableCarsAction has happened. Can not parse dates. ", e);
                return JspPagePath.ERROR_PAGE;
            }
            int period = endDate.getDay() - startDate.getDay();
            if (period < MIN_PERIOD) {
                period = MIN_PERIOD;
            }
            if (allCarsInOrder.size() < COUNT_ONE) {
                availableCars.addAll(allCars);
            } else {
                getAvailableCarList(availableCars, allCarsInOrder, allCars, startDate, endDate);
            }
            request.setAttribute(ALL_CARS_LIST, availableCars);
            request.setAttribute(ORDER_PERIOD, period);
            request.setAttribute(ORDER_START_DATE, startDate);
            request.setAttribute(ORDER_END_DATE, endDate);
        } catch (SQLException e) {
            LOG.error("Exception in ShowAvailableCarsAction has happened. Can not get cars from DB. ", e);
            return JspPagePath.ERROR_PAGE;
        } finally {
            connectionPool.freeConnection(connection);
        }
        return forward;
    }

    private void getAvailableCarList(List<Car> availableCars, List<Car> orderedCarList,
                                     List<Car> allCars, java.sql.Date startDate, java.sql.Date endDate) {
        List<Car> bookedCars = new ArrayList<>();
        for (Car carInOrderList : orderedCarList) {
            Date carStartDate = carInOrderList.getStartDate();
            Date carEndDate = carInOrderList.getEndDate();
            if (startDate.after(carEndDate) || endDate.before(carStartDate)) {
            } else {
                bookedCars.add(carInOrderList);
            }
        }
        for (Car car : allCars) {
            int count = COUNT_ZERO;
            for (Car bookedCar : bookedCars) {
                if (car.getId() == bookedCar.getId()) {
                    count++;
                }
            }
            if (count < COUNT_ONE) {
                if (car.getIsAvailable().equalsIgnoreCase(CAR_AVAILABLE_YES)) {
                    availableCars.add(car);
                }
            }
        }
    }
}