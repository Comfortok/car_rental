package com.epam.action;

import com.epam.constant.JspPagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSignInPageAction implements IAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return JspPagePath.LOGIN_PAGE;
    }
}
