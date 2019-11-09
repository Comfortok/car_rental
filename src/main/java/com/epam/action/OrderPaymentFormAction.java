package com.epam.action;

import com.epam.Path;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.action.ConstantField.ORDER_ID;
import static com.epam.action.ConstantField.PAYMENT_SUM;

public class OrderPaymentFormAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter(ORDER_ID);
        String paymentSum = request.getParameter(PAYMENT_SUM);
        request.setAttribute(ORDER_ID, orderId);
        request.setAttribute(PAYMENT_SUM, paymentSum);
        return Path.PAYMENT_FORM_PAGE;
    }
}