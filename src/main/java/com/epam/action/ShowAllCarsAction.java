package com.epam.action;

import com.epam.dao.CarDAO;
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
        CarDAO carDAO;
        List<Car> cars;

            carDAO = new CarDAO();
            cars = carDAO.getAll();
            request.setAttribute("availableCars", cars);
        return ALL_CARS_PAGE;
    }
}
