package com.epam.action;

import com.epam.constant.JspPagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSignUpPageAction implements IAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return JspPagePath.REGISTER_PAGE;
    }
}
