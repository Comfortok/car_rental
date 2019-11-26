package com.epam.action;

import com.epam.constant.JspPagePath;
import com.epam.dao.impl.UserDAO;
import com.epam.entity.User;
import com.epam.pool.ConnectionPool;
import com.epam.util.Validator;
import com.epam.util.PasswordHashing;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.epam.constant.ConstantField.*;

public class RegisterAction implements IAction {
    private static final Logger LOG = Logger.getLogger(RegisterAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("RegisterAction execute starts.");
        UserDAO userDAO = new UserDAO();
        String forward = JspPagePath.LOGIN_PAGE;
        HttpSession session = request.getSession();
        User user = new User();
        String email = request.getParameter(USER_EMAIL);
        user.setEmail(email);
        String password = request.getParameter(USER_PASSWORD);
        String passwordConfirm = request.getParameter(USER_CONFIRM_PASSWORD);
        if (!password.equals(passwordConfirm)) {
            request.setAttribute(CONFIRM_PASSWORD_ERROR, CONFIRM_PASSWORD_ERROR_MESSAGE);
            forward = JspPagePath.REGISTER_PAGE;
        } else if (email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            request.setAttribute(EMPTY_FIELD_ERROR, EMPTY_FIELD_ERROR_MESSAGE);
            forward = JspPagePath.REGISTER_PAGE;
        } else if (Validator.validateRegistrationInfo(email, password)) {
            request.setAttribute(VALIDATION_ERROR, VALIDATION_ERROR_MESSAGE);
            forward = JspPagePath.REGISTER_PAGE;
        } else {
            ConnectionPool connectionPool = null;
            Connection connection = null;
            try {
                connectionPool = ConnectionPool.getInstance();
                connection = connectionPool.getConnection();
                password = PasswordHashing.getHashValue(password);
                user.setPassword(password);
                List<User> users = userDAO.getAll(connection);
                for (User userSecond : users) {
                    if (userSecond.getEmail().equals(email)) {
                        request.setAttribute(EMAIL_ERROR, EMAIL_ERROR_MESSAGE);
                        forward = JspPagePath.REGISTER_PAGE;
                    }
                }
                if (forward.equals(JspPagePath.LOGIN_PAGE)) {
                    userDAO.insert(user, connection);
                    session.setAttribute(USER_EMAIL, user.getEmail());
                    forward = JspPagePath.LOGIN_PAGE;
                }
            } catch (SQLException e) {
                LOG.error("Exception in RegisterAction has happened. Can not get user listfrom DB. ", e);
                return JspPagePath.ERROR_PAGE;
            } finally {
                connectionPool.freeConnection(connection);
            }
        }
        return forward;
    }
}