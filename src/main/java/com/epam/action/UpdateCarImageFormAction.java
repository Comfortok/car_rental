package com.epam.action;

import com.epam.Path;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.action.ConstantField.CAR_ID;

public class UpdateCarImageFormAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String carId = request.getParameter(CAR_ID);
        request.setAttribute(CAR_ID, carId);
        return Path.ADMIN_UPDATE_CAR_IMAGE;
    }
}