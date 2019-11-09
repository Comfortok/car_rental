package com.epam.action;

import com.epam.Path;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.epam.action.ConstantField.EMPTY_ACTION_ERROR;
import static com.epam.action.ConstantField.EMPTY_ACTION_MESSAGE;

public class EmptyAction implements Action {
    private static final Logger LOG = Logger.getLogger(EmptyAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("EmptyAction starts.");
        request.setAttribute(EMPTY_ACTION_ERROR, EMPTY_ACTION_MESSAGE);
        LOG.error("Set the request attribute: errorMessage --> " + EMPTY_ACTION_MESSAGE);
        LOG.debug("EmptyAction has finished.");
        return Path.HOME_PAGE;
    }
}