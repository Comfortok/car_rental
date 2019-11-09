package com.epam.action;

import com.epam.Path;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.action.ConstantField.*;

public class UpdateCarFormAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String carId = request.getParameter(CAR_ID);
        String mileage = request.getParameter(MILEAGE);
        String carNumber = request.getParameter(CAR_REGISTERED_NUMBER);
        request.setAttribute(CAR_ID, carId);
        request.setAttribute(MILEAGE, mileage);
        request.setAttribute(CAR_REGISTERED_NUMBER, carNumber);
        return Path.ADMIN_UPDATE_CAR;
    }
}