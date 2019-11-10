package com.epam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public interface IAction extends Serializable {
    String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;
}