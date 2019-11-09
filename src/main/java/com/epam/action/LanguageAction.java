package com.epam.action;

import com.epam.Path;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import static com.epam.action.ConstantField.*;

public class LanguageAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String command = request.getParameter(COMMAND);
        Locale locale = null;
        switch (command.toUpperCase()) {
            case ENGLISH:
                locale = new Locale(ENGLISH.toLowerCase());
                break;
            case RUSSIAN:
                locale = new Locale(RUSSIAN.toLowerCase());
                break;
        }
        session.setAttribute(LOCALE_ATTRIBUTE, locale);
        return Path.HOME_PAGE;
    }
}