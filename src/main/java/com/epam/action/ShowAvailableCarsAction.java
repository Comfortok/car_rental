package com.epam.action;

import com.epam.dao.CarDao;
import com.epam.dao.impl.CarDaoImpl;
import com.epam.entity.Car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.Path.AVAILABLE_CARS_PAGE;

public class ShowAvailableCarsAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        CarDaoImpl carDaoImpl;
        List<Car> allCars;
        List<Car> allCarsInOrder;
        List<Car> availableCars = new ArrayList<>();
        carDaoImpl = new CarDaoImpl();
        
        System.out.println("showAvCars exe");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date userStartDate = null;
        Date userEndDate = null;
        java.sql.Date startDate = null;
        java.sql.Date endDate = null;
        try {
            userStartDate = format.parse(request.getParameter("startDate"));
            userEndDate = format.parse(request.getParameter("endDate"));
            startDate = new java.sql.Date(userStartDate.getTime());
            endDate = new java.sql.Date(userEndDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int period = endDate.getDay() - startDate.getDay();
        allCars = carDaoImpl.getAll();
        System.out.println("allCars: " + allCars);
        allCarsInOrder = carDaoImpl.getAvailable();
        System.out.println("allCarsOrder: " + allCarsInOrder);

        for (Car car : allCarsInOrder) {
            System.out.println("foreach...");
            Date carStartDate = car.getStartDate();
            Date carEndDate = car.getEndDate();
            if ((startDate.after(carEndDate) || endDate.before(carStartDate)) && (car.getIsAvailable().toLowerCase().equals("yes"))) {
                System.out.println("This car is available: " + car.getModel());
                availableCars.add(car);
            }
        }

        for (Car allCar : allCars) {
            int count = 0;
            for (Car orderCar : allCarsInOrder) {
                if (orderCar.getId() == allCar.getId()) {
                    count++;
                }
            }
            if (count < 1) {
                availableCars.add(allCar);
            }
        }

        System.out.println("available cars: " + availableCars);
        request.setAttribute("availableCars", availableCars);
        request.setAttribute("period", period);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        return AVAILABLE_CARS_PAGE;
    }
}