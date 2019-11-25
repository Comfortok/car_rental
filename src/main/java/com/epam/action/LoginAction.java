package com.epam.action;

import com.epam.constant.JspPagePath;
import com.epam.dao.impl.UserDAO;
import com.epam.entity.User;
import com.epam.pool.ConnectionPool;
import com.epam.util.PasswordHashing;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;

import static com.epam.constant.ConstantField.*;

public class LoginAction implements IAction {
    private static final Logger LOG = Logger.getLogger(LoginAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDAO userDAO = new UserDAO();
        String forward = JspPagePath.HOME_PAGE;
        HttpSession session = request.getSession();
        User user;
        String email = request.getParameter(USER_EMAIL);
        String password = request.getParameter(USER_PASSWORD);
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            request.setAttribute(EMPTY_FIELD_ERROR, EMPTY_FIELD_ERROR_MESSAGE);
            forward = JspPagePath.LOGIN_PAGE;
        } else {
            ConnectionPool connectionPool = null;
            Connection connection = null;
            password = PasswordHashing.getHashValue(password);
            try {
                connectionPool = ConnectionPool.getInstance();
                connection = connectionPool.getConnection();
                user = userDAO.getByEmail(email, connection);
                if (user == null || !password.equals(user.getPassword())) {
                    request.setAttribute(NO_SUCH_USER_ERROR, NO_SUCH_USER_ERROR_MESSAGE);
                    forward = JspPagePath.LOGIN_PAGE;
                } else {
                    session.setAttribute(USER_ATTRIBUTE, user);
                    session.setAttribute(USER_ROLE_ATTRIBUTE, user.getRole().getId());
                    session.setAttribute(USER_ID, user.getId());
                }
            } catch (Exception e) {
                LOG.error("Exception in LoginAction has happened. Can not get a user by email. ", e);
                return JspPagePath.ERROR_PAGE;
            } finally {
                connectionPool.freeConnection(connection);
            }
        }
        return forward;
    }
}