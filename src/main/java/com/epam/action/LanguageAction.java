package com.epam.action;

import com.epam.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

public class LanguageAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("Lang exe");
        HttpSession session = request.getSession();
        System.out.println("session: " + session.getId());
        String command = request.getParameter("command");
        String getContPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String page = servletPath + "?command=" + command;

        System.out.println("getContPath: " + getContPath);
        System.out.println("serPath: " + servletPath);
        String sesCont = String.valueOf(session.getSessionContext());
        System.out.println("sesCont: " + sesCont);
        System.out.println("command: " + command);
        Locale locale = null;
        switch (command.toUpperCase()) {
            case "EN":
                System.out.println("en");
                locale = new Locale("en");
                break;
            case "RU":
                System.out.println("ru");
                locale = new Locale("ru");
                break;
        }
        session.setAttribute("locale", locale);
        System.out.println("locale 2: " + locale.getDisplayLanguage());
        return Path.HOME_PAGE;
    }
}