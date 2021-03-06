package com.epam.action;

import com.epam.constant.JspPagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.constant.ConstantField.*;

public class UpdateCarFormAction implements IAction {
    private static final Logger LOG = Logger.getLogger(UpdateCarFormAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("UpdateCarFormAction execute starts.");
        String carId = request.getParameter(CAR_ID);
        String mileage = request.getParameter(MILEAGE);
        String carNumber = request.getParameter(CAR_REGISTERED_NUMBER);
        request.setAttribute(CAR_ID, carId);
        request.setAttribute(MILEAGE, mileage);
        request.setAttribute(CAR_REGISTERED_NUMBER, carNumber);
        return JspPagePath.ADMIN_UPDATE_CAR;
    }
}