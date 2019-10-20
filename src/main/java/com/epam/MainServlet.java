package com.epam;

import com.epam.action.Action;
import com.epam.action.ActionFactory;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MainServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(MainServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("dopost");
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DOGET");
        process(request, response);
    }

    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        String actionName = request.getParameter("command");
        System.out.println("actionName: " + actionName);
        Action action = ActionFactory.getAction(actionName);
        System.out.println("action: " + action);
        String forward = Path.ERROR_PAGE;
        System.out.println("first forward: " + forward);

        try {
            forward = action.execute(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
        LOG.trace("Forward address: " + forward);
        LOG.debug("Servlet has finished, now go to: " + forward);
        System.out.println("forwarding to: " + forward);
        request.getRequestDispatcher(forward).forward(request, response);
    }
}