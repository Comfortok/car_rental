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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.epam.constant.ConstantField.*;

public class AddCarFormAction implements IAction {
    private static final Logger LOG = Logger.getLogger(AddCarFormAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forward = JspPagePath.ADMIN_ADD_CAR_PAGE;
        ConnectionPool connectionPool = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            CarDAO carDAO = new CarDAO();
            List<Car> carList = new ArrayList<>(carDAO.getAll(connection));
            Set<String> brandList = new HashSet<>();
            Set<String> modelList = new HashSet<>();
            Set<String> colorList = new HashSet<>();
            Set<String> categoryList = new HashSet<>();
            Set<String> transmissionList = new HashSet<>();
            Set<String> bodyList = new HashSet<>();
            Set<String> engineList = new HashSet<>();
            for (Car car : carList) {
                brandList.add(car.getModel().getBrand().getName());
                modelList.add(car.getModel().getName());
                colorList.add(car.getColor().getName());
                categoryList.add(car.getCategory().getName());
                transmissionList.add(car.getTransmission().getName());
                bodyList.add(car.getBody().getName());
                engineList.add(car.getEngineType().getName());
            }
            request.setAttribute(BRAND_LIST, brandList);
            request.setAttribute(MODEL_LIST, modelList);
            request.setAttribute(COLOR_LIST, colorList);
            request.setAttribute(CATEGORY_LIST, categoryList);
            request.setAttribute(TRANSMISSION_LIST, transmissionList);
            request.setAttribute(BODY_LIST, bodyList);
            request.setAttribute(ENGINE_LIST, engineList);
        } catch (SQLException e) {
            LOG.error("Exception in AddCarFormAction has happened. Can not get car list from DB. ", e);
            return JspPagePath.ERROR_PAGE;
        } finally {
            connectionPool.freeConnection(connection);
        }
        return forward;
    }
}