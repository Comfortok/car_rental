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

public class LoginAction implements Action {
    private static final long serialVersionUID = -1071012092354190852L;
    private static final Logger LOG = Logger.getLogger(LoginAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("execute in LoginAction...");
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        System.out.println("userDAO: " + userDaoImpl);
        String forward = Path.HOME_PAGE;
        HttpSession session = request.getSession();

        User user;
        //email and pass from req
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("email and pass: " + email + ", " + password);

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            String errorMessage = "Fields are empty";
            System.out.println(errorMessage);
            session.setAttribute("errorMessage", errorMessage);
            forward = Path.ERROR_PAGE;
            return forward;
        }

            user = userDaoImpl.getByEmail(email);
            System.out.println("found in db user " + user);

            if (user == null || !password.equals(user.getPassword())) {
                String errorMessage = "Cannot find user with such login or password";
                System.out.println(errorMessage);
                session.setAttribute("errorMessage", errorMessage);
                forward = Path.ERROR_PAGE;
                return forward;
            }
            session.setAttribute("user", user);
            session.setAttribute("roleId", user.getRole().getId());
            session.setAttribute("userId", user.getId());
            System.out.println(user.getId() + user.getEmail() + user.getPassword());
        return forward;
    }
}