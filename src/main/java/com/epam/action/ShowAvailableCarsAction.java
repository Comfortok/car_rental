package com.epam.action;

import com.epam.dao.impl.CarDaoImpl;
import com.epam.entity.Car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.epam.Path;
import org.apache.log4j.Logger;

import static com.epam.action.ConstantField.*;

public class ShowAvailableCarsAction implements Action {
    private static final Logger LOG = Logger.getLogger(ShowAvailableCarsAction.class);
    private static final int MIN_PERIOD = 1;
    private static final int COUNT_ZERO = 0;
    private static final int COUNT_ONE = 1;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("ShowAvailableCarsAction execute starts.");
        CarDaoImpl carDaoImpl = new CarDaoImpl();
        List<Car> availableCars;
        List<Car> allCars = carDaoImpl.getAll();
        List<Car> allCarsInOrder = carDaoImpl.getAvailable();
        java.sql.Date startDate = null;
        java.sql.Date endDate = null;
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date userStartDate;
        Date userEndDate;
        try {
            userStartDate = format.parse(request.getParameter(ORDER_START_DATE));
            userEndDate = format.parse(request.getParameter(ORDER_END_DATE));
            startDate = new java.sql.Date(userStartDate.getTime());
            endDate = new java.sql.Date(userEndDate.getTime());
        } catch (ParseException e) {
            LOG.error(e);
            e.printStackTrace();
        }
        int period = endDate.getDay() - startDate.getDay();
        if (period < MIN_PERIOD) {
            period = MIN_PERIOD;
        }
        if (allCarsInOrder.size() < COUNT_ONE) {
            availableCars = new ArrayList<>(allCars);
        } else {
            List<Car> bookedCars = new ArrayList<>();
            availableCars = new ArrayList<>();
            for (Car carInOrderList : allCarsInOrder) {
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
                    if (car.getIsAvailable().toLowerCase().equals(CAR_AVAILABLE_YES)) {
                        availableCars.add(car);
                    }
                }
            }
        }
        request.setAttribute(ALL_CARS_LIST, availableCars);
        request.setAttribute(ORDER_PERIOD, period);
        request.setAttribute(ORDER_START_DATE, startDate);
        request.setAttribute(ORDER_END_DATE, endDate);
        return Path.ALL_CARS_PAGE;
    }
}