package com.epam.action;

import com.epam.Path;
import com.epam.dao.impl.CarDaoImpl;
import com.epam.entity.Car;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.action.ConstantField.ALL_CARS_LIST;

public class ShowAllCarsAction implements Action {
    private static final Logger LOG = Logger.getLogger(ShowAllCarsAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("ShowAllCarsAction execute starts.");
        CarDaoImpl carDaoImpl = new CarDaoImpl();
        List<Car> cars = carDaoImpl.getAll();
        request.setAttribute(ALL_CARS_LIST, cars);
        return Path.ALL_CARS_PAGE;
    }
}