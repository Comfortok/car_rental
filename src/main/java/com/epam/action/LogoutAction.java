package com.epam.action;

import com.epam.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements IAction {
    private static final Logger LOG = Logger.getLogger(LoginAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("LogoutAction execute starts.");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return Path.LOGIN_PAGE;
    }
}