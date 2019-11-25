package com.epam.servlet;

import com.epam.action.IAction;
import com.epam.action.ActionFactory;
import com.epam.constant.JspPagePath;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.epam.constant.ConstantField.COMMAND;
import static com.epam.constant.ConstantField.ERROR_MESSAGE;

public class MainServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(MainServlet.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Servlet.process()");
        String actionName = request.getParameter(COMMAND);
        IAction action = ActionFactory.getAction(actionName);
        String forward = JspPagePath.ERROR_PAGE;
        try {
            forward = action.execute(request, response);
        } catch (SQLException e) {
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
        }
        LOG.trace("Forward address: " + forward);
        LOG.debug("Servlet has finished, now going to: " + forward);
        request.getRequestDispatcher(forward).forward(request, response);
    }
}