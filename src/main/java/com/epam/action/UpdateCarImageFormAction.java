package com.epam.action;

import com.epam.constant.JspPagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.constant.ConstantField.CAR_ID;

public class UpdateCarImageFormAction implements IAction {
    private static final Logger LOG = Logger.getLogger(UpdateCarImageFormAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("UpdateCarImageFormAction execute starts.");
        String carId = request.getParameter(CAR_ID);
        HttpSession session = request.getSession();
        session.setAttribute(CAR_ID, carId);
        return JspPagePath.ADMIN_UPDATE_CAR_IMAGE;
    }
}