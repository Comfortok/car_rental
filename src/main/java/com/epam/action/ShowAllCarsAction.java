package com.epam.action;

import com.epam.constant.JspPagePath;
import com.epam.dao.impl.CarDAO;
import com.epam.entity.Car;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.epam.constant.ConstantField.ALL_CARS_LIST;

public class ShowAllCarsAction implements IAction {
    private static final Logger LOG = Logger.getLogger(ShowAllCarsAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("ShowAllCarsAction execute starts.");
        CarDAO carDAO = new CarDAO();
        ConnectionPool connectionPool = null;
        Connection connection = null;
        List<Car> cars;
        String forward = JspPagePath.ALL_CARS_PAGE;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            cars = carDAO.getAll(connection);
            request.setAttribute(ALL_CARS_LIST, cars);
        } catch (SQLException e) {
            LOG.error("Exception in ShowAllCarsAction has happened. Can not get cars from DB. ", e);
            return JspPagePath.ERROR_PAGE;
        } finally {
            connectionPool.freeConnection(connection);
        }
        return forward;
    }
}