package com.epam.action;

import com.epam.constant.JspPagePath;
import com.epam.dao.impl.CarDAO;
import com.epam.entity.Car;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.SQLException;

import static com.epam.constant.ConstantField.*;

public class UpdateCarAction implements IAction {
    private static final Logger LOG = Logger.getLogger(UpdateCarAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ShowAllCarsAction showAllCarsAction = new ShowAllCarsAction();
        String forward = showAllCarsAction.execute(request, response);
        long carId = Long.parseLong(request.getParameter(CAR_ID));
        String stringMileage = request.getParameter(CAR_MILEAGE);
        String currentStringMileage = request.getParameter(MILEAGE);
        String carNumber = request.getParameter(CAR_NUMBER);
        String currentCarNumber = request.getParameter(CAR_REGISTERED_NUMBER);
        String available = request.getParameter(CAR_AVAILABILITY);
        Car car = new Car();
        car.setId(carId);
        car.setAvailable(available);
        if (stringMileage.isEmpty() && carNumber.isEmpty()) {
            car.setRegisteredNumber(currentCarNumber);
            car.setMileage(Integer.parseInt(currentStringMileage));
        } else if (carNumber.isEmpty()) {
            car.setRegisteredNumber(currentCarNumber);
            car.setMileage(Integer.parseInt(stringMileage));
        } else if (stringMileage.isEmpty()) {
            car.setRegisteredNumber(carNumber);
            car.setMileage(Integer.parseInt(currentStringMileage));
        } else {
            car.setRegisteredNumber(currentCarNumber);
            car.setMileage(Integer.parseInt(currentStringMileage));
        }
        ConnectionPool connectionPool = null;
        Connection connection = null;
        CarDAO carDao = new CarDAO();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            carDao.update(car, connection);
        } catch (SQLException e) {
            LOG.error("Exception in UpdateCarAction has happened. Can not update car info. ", e);
            return JspPagePath.ERROR_PAGE;
        } finally {
            connectionPool.freeConnection(connection);
        }
        return forward;
    }
}