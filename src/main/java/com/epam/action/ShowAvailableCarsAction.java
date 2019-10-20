package com.epam.action;

import com.epam.dao.CarDAO;
import com.epam.dao.OrderDAO;
import com.epam.entity.Car;
import com.epam.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.Path.AVAILABLE_CARS_PAGE;

public class ShowAvailableCarsAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        CarDAO carDAO;
        List<Car> allCars;
        List<Car> allCarsInOrder;
        List<Car> availableCars = new ArrayList<>();
        carDAO = new CarDAO();
        
        System.out.println("showAvCars exe");
        Date userStartDate = Date.valueOf(request.getParameter("startDate"));
        Date userEndDate = Date.valueOf(request.getParameter("endDate"));
        allCars = carDAO.getAll();
        System.out.println("allCars: " + allCars);
        allCarsInOrder = carDAO.getAvailable();
        System.out.println("allCarsOrder: " + allCarsInOrder);

        for (Car car : allCarsInOrder) {
            System.out.println("foreach...");
            Date carStartDate = car.getStartDate();
            Date carEndDate = car.getEndDate();
            if ((userStartDate.after(carEndDate) || userEndDate.before(carStartDate)) && (car.getIsAvailable().toLowerCase().equals("yes"))) {
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
        return AVAILABLE_CARS_PAGE;
    }
}