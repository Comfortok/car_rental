package com.epam.action;

import com.epam.dao.impl.CarDaoImpl;
import com.epam.entity.Car;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.action.ConstantField.*;

public class UpdateCarImageAction implements Action {
    private static final Logger LOG = Logger.getLogger(UpdateCarImageAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("UpdateCarImageAction execute starts.");
        String image = request.getParameter(CAR_IMAGE);
        long carId = Long.parseLong(request.getParameter(CAR_ID));
        String forward;
        if (image.isEmpty()) {
            request.setAttribute(EMPTY_FIELD_ERROR, EMPTY_FIELD_ERROR_MESSAGE);
            request.setAttribute(CAR_ID, carId);
            UpdateCarImageFormAction updateCarImageFormAction = new UpdateCarImageFormAction();
            forward = updateCarImageFormAction.execute(request, response);
        } else {
            image = CAR_IMAGE_FOLDER.concat(image).concat(CAR_IMAGE_FORMAT);
            Car car = new Car();
            car.setId(carId);
            car.setImageName(image);
            CarDaoImpl carDao = new CarDaoImpl();
            carDao.updateImage(car);
            ShowAllCarsAction showAllCarsAction = new ShowAllCarsAction();
            forward = showAllCarsAction.execute(request, response);
        }
        return forward;
    }
}