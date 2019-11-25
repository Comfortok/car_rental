package com.epam.action;

import com.epam.constant.JspPagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.constant.ConstantField.*;

public class OrderFormAction implements IAction {
    private static final Logger LOG = Logger.getLogger(OrderFormAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("OrderFormAction execute starts.");
        String forward = JspPagePath.ORDER_FORM_PAGE;
        long userId = (long) request.getSession().getAttribute(USER_ID);
        long carId = Long.parseLong(request.getParameter(CAR_ID));
        double carPrice = Double.parseDouble(request.getParameter(CAR_PRICE));
        int period = Integer.parseInt(request.getParameter(ORDER_PERIOD));
        double sumToPay = carPrice * period;
        String carModel = request.getParameter(CAR_MODEL);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date parseDateStart;
        Date parseDateEnd;
        java.sql.Date sqlDateStart;
        java.sql.Date sqlDateEnd;
        try {
            parseDateStart = format.parse(request.getParameter(ORDER_START_DATE));
            parseDateEnd = format.parse(request.getParameter(ORDER_END_DATE));
            sqlDateStart = new java.sql.Date(parseDateStart.getTime());
            sqlDateEnd = new java.sql.Date(parseDateEnd.getTime());
        } catch (ParseException e) {
            LOG.error("Exception in OrderFormAction has happened. Can not parse dates. ", e);
            return JspPagePath.ERROR_PAGE;
        }
        request.setAttribute(USER_ID, userId);
        request.setAttribute(CAR_ID, carId);
        request.setAttribute(CAR_PRICE, carPrice);
        request.setAttribute(ORDER_PAYMENT_SUM, sumToPay);
        request.setAttribute(ORDER_START_DATE, sqlDateStart);
        request.setAttribute(ORDER_END_DATE, sqlDateEnd);
        request.setAttribute(CAR_MODEL, carModel);
        return forward;
    }
}