package com.epam.action;

import com.epam.dao.impl.CarDAO;
import com.epam.entity.Car;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.action.ConstantField.*;

public class UpdateCarAction implements IAction {
    private static final Logger LOG = Logger.getLogger(UpdateCarAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("UpdateCarAction execute starts.");
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
        CarDAO carDao = new CarDAO();
        carDao.update(car);
        ShowAllCarsAction showAllCarsAction = new ShowAllCarsAction();
        return showAllCarsAction.execute(request, response);
    }
}