package com.epam.action;

import com.epam.constant.JspPagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.constant.ConstantField.EMPTY_ACTION_ERROR;
import static com.epam.constant.ConstantField.EMPTY_ACTION_MESSAGE;

public class EmptyAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(EMPTY_ACTION_ERROR, EMPTY_ACTION_MESSAGE);
        return JspPagePath.HOME_PAGE;
    }
}