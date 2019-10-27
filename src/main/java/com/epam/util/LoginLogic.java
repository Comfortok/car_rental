/*
package com.epam.util;

import com.epam.entity.User;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LoginLogic {

    public static int checkLogin(List<User> clients, String login, String password, HttpServletRequest request) {
        String login2;
        String password2;
        HttpSession httpSession = request.getSession();

        if (clients != null) {
            for (User client : clients) {
                if (client != null) {
                    login2 = client.getEmail();
                    password2 = client.getPassword();
                    if (login.equals(login2) & password.equals(password2)) {
                        
                        httpSession.setAttribute("user", client);
                        httpSession.setAttribute("userType", client.getType());
                        httpSession.setAttribute("userName", client.getLogin());
                        httpSession.setAttribute("userEmail", client.getEmail());
                        httpSession.setAttribute("userId", client.getId());
                        httpSession.setAttribute("userPassNum", client.getPassNum());
                        httpSession.setAttribute("userSurname", client.getSurname());
                        httpSession.setAttribute("userRealName", client.getName());
                        httpSession.setAttribute("credit", client.getCredit());
                        
                        if (client.getType().equals(ClientType.ADMIN)) {
                            return 2;
                        } else {
                            return 3;
                        }
                    }

                }
            }
        }
        return 0;
    }

}
*/
