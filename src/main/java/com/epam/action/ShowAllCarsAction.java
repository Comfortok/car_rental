package com.epam.action;

import com.epam.dao.CarDao;
import com.epam.dao.impl.CarDaoImpl;
import com.epam.entity.Car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.epam.Path.ALL_CARS_PAGE;

public class ShowAllCarsAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        CarDaoImpl carDaoImpl;
        List<Car> cars;

        carDaoImpl = new CarDaoImpl();
            cars = carDaoImpl.getAll();
            request.setAttribute("availableCars", cars);
        return ALL_CARS_PAGE;
    }
}
