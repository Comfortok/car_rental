package com.epam.action;

import com.epam.Path;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class LogoutAction implements Action {
    private static final Logger LOG = Logger.getLogger(LoginAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("LogoutAction starts.");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        LOG.debug("LogoutAction has finished");
        return Path.LOGIN_PAGE;
    }
}