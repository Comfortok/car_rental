package com.epam.action;

import com.epam.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class EmptyAction implements Action {
    private static final Logger LOG = Logger.getLogger(EmptyAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        LOG.debug("EmptyAction starts.");
        String errorMessage = "No such action.";
        request.setAttribute("errorMessage", errorMessage);
        LOG.error("Set the request attribute: errorMessage --> " + errorMessage);
        LOG.debug("EmptyAction has finished.");
        return Path.HOME_PAGE;
    }
}