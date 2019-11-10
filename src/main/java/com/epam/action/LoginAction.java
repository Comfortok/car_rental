package com.epam.action;

import com.epam.Path;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.util.PasswordHashing;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.action.ConstantField.*;

public class LoginAction implements Action {
    private static final long serialVersionUID = -1071012092354190852L;
    private static final Logger LOG = Logger.getLogger(LoginAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("LoginAction execute starts.");
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        String forward = Path.HOME_PAGE;
        HttpSession session = request.getSession();
        User user;
        String email = request.getParameter(USER_EMAIL);
        String password = request.getParameter(USER_PASSWORD);
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            request.setAttribute(EMPTY_FIELD_ERROR, EMPTY_FIELD_ERROR_MESSAGE);
            forward = Path.LOGIN_PAGE;
        } else {
            password = PasswordHashing.getHashValue(password);
            user = userDaoImpl.getByEmail(email);
            if (user == null || !password.equals(user.getPassword())) {
                request.setAttribute(NO_SUCH_USER_ERROR, NO_SUCH_USER_ERROR_MESSAGE);
                forward = Path.LOGIN_PAGE;
            } else {
                session.setAttribute(USER_ATTRIBUTE, user);
                session.setAttribute(USER_ROLE_ATTRIBUTE, user.getRole().getId());
                session.setAttribute(USER_ID, user.getId());
            }
        }
        return forward;
    }
}