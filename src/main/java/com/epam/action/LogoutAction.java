package com.epam.action;

import com.epam.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Locale;

public class LogoutAction implements Action {
    private static final Logger LOG = Logger.getLogger(LoginAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("LogoutAction starts.");
        System.out.println("execute in LogoutAction");
        Locale locale = request.getLocale();
        System.out.println("cur locale: " + locale);
        HttpSession session = request.getSession(false);

        System.out.println("session id: " + session.getId());
        if (session != null) {
            session.invalidate();
            System.out.println("ses is invalid");
            System.out.println("locale in if: " + locale);
        }
        System.out.println("session id after if/null: " + session.getId());
        System.out.println("LogountAction finished");
        LOG.debug("LogoutAction has finished");
        return Path.HOME_PAGE;
    }
}