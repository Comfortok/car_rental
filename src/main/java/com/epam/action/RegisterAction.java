package com.epam.action;

import com.epam.Path;
import com.epam.dao.impl.UserDAO;
import com.epam.entity.User;
import com.epam.util.Validator;
import com.epam.util.PasswordHashing;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.epam.action.ConstantField.*;

public class RegisterAction implements IAction {
    private static final Logger LOG = Logger.getLogger(RegisterAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("RegisterAction execute starts.");
        UserDAO userDAO = new UserDAO();
        String page = Path.LOGIN_PAGE;
        HttpSession session = request.getSession();
        User user = new User();
        String email = request.getParameter(USER_EMAIL);
        user.setEmail(email);
        String password = request.getParameter(USER_PASSWORD);
        String passwordConfirm = request.getParameter(USER_CONFIRM_PASSWORD);
        if (!password.equals(passwordConfirm)) {
            request.setAttribute(CONFIRM_PASSWORD_ERROR, CONFIRM_PASSWORD_ERROR_MESSAGE);
            page = Path.REGISTER_PAGE;
        } else if (email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            request.setAttribute(EMPTY_FIELD_ERROR, EMPTY_FIELD_ERROR_MESSAGE);
            page = Path.REGISTER_PAGE;
        } else if (Validator.validateRegistrationInfo(email, password)) {
            request.setAttribute(VALIDATION_ERROR_MESSAGE, VALIDATION_ERROR);
            page = Path.REGISTER_PAGE;
        } else {
            password = PasswordHashing.getHashValue(password);
            user.setPassword(password);
            List<User> users = userDAO.getAll();
            for (User userSecond : users) {
                if (userSecond.getEmail().equals(email)) {
                    request.setAttribute(EMAIL_ERROR, EMAIL_ERROR_MESSAGE);
                    page = Path.REGISTER_PAGE;
                }
            }
            if (page.equals(Path.LOGIN_PAGE)) {
                userDAO.insert(user);
                session.setAttribute(USER_EMAIL, user.getEmail());
                page = Path.LOGIN_PAGE;
            }
        }
        return page;
    }
}