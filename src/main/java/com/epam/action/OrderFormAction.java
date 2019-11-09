package com.epam.action;

import com.epam.Path;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.epam.action.ConstantField.*;

public class OrderFormAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long userId = (long) request.getSession().getAttribute(USER_ID);
        long carId = Long.parseLong(request.getParameter(CAR_ID));
        double carPrice = Double.parseDouble(request.getParameter(CAR_PRICE));
        int period = Integer.parseInt(request.getParameter(ORDER_PERIOD));
        double sumToPay = carPrice * period;
        String carModel = request.getParameter(CAR_MODEL);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date parseDateStart;
        Date parseDateEnd;
        java.sql.Date sqlDateStart = null;
        java.sql.Date sqlDateEnd = null;
        try {
            parseDateStart = format.parse(request.getParameter(ORDER_START_DATE));
            parseDateEnd = format.parse(request.getParameter(ORDER_END_DATE));
            sqlDateStart = new java.sql.Date(parseDateStart.getTime());
            sqlDateEnd = new java.sql.Date(parseDateEnd.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        request.setAttribute(USER_ID, userId);
        request.setAttribute(CAR_ID, carId);
        request.setAttribute(CAR_PRICE, carPrice);
        request.setAttribute(ORDER_PAYMENT_SUM, sumToPay);
        request.setAttribute(ORDER_START_DATE, sqlDateStart);
        request.setAttribute(ORDER_END_DATE, sqlDateEnd);
        request.setAttribute(CAR_MODEL, carModel);
        return Path.ORDER_FORM_PAGE;
    }
}