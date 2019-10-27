package com.epam.action;

import com.epam.Path;
import com.epam.dao.UserDao;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RegisterAction implements Action {
    private static final Logger LOG = Logger.getLogger(RegisterAction.class);
    String forward;
    User user;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("register execute");
        System.out.println("creating userdao");
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        System.out.println("userdao: " + userDaoImpl);
        forward = Path.LOGIN_PAGE;
        LOG.debug("RegisterCommand starts");
        HttpSession session = request.getSession();
        System.out.println("session id: " + session.getId());
        user = new User();
        System.out.println("user is " + user);
        String email = request.getParameter("email");
        user.setEmail(email);
        System.out.println("email: " + email);
        LOG.trace("Request parameter: loging --> " + user.getEmail());
        String password = request.getParameter("password");
        user.setPassword(password);
        System.out.println("pass: " + password);
        LOG.trace("Request parameter: password --> " + user.getPassword());
        String passwordConfirm = request.getParameter("password_confirm");

        if (email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            System.out.println("Empty lines");
            String errorMessage = "fill the whole lines...";
            session.setAttribute("errorMessage", errorMessage);
            LOG.error("Set the session attribute: errorMessage --> "
                    + errorMessage);
            forward = Path.ERROR_PAGE;
            LOG.debug("RegisterCommand finished");
            return forward;
        }

        System.out.println("trying to create list...(2)");
        List<User> users = userDaoImpl.getAll();
        System.out.println("userlist created 2: " + users);
        for (User user2 : users) {
            System.out.println("another user: " + user2);
            if (user2.getEmail().equals(email)) {
                String errorMessage = "Email is already exist";
                session.setAttribute("errorMessage", errorMessage);
                LOG.error("Set the session attribute: errorMessage --> "
                        + errorMessage);
                System.out.println("Email exist");
                forward = Path.ERROR_PAGE;
                LOG.debug("RegisterCommand finished");
                return forward;
            }
        }

        System.out.println("inserting user to db");
        userDaoImpl.insert(user);
        LOG.trace("Create new user --> " + user);
        session.setAttribute("email", user.getEmail());
        System.out.println("forwarding to login page");
        forward = Path.LOGIN_PAGE;
        LOG.debug("RegisterCommand finished");
        return forward;
    }
}