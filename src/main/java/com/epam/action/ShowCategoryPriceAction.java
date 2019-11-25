package com.epam.action;

import com.epam.constant.JspPagePath;
import com.epam.dao.impl.CarDAO;
import com.epam.entity.CarCategory;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.epam.constant.ConstantField.*;

public class ShowCategoryPriceAction implements IAction {
    private static final Logger LOG = Logger.getLogger(ShowCategoryPriceAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forward = JspPagePath.CHANGE_PRICE_PAGE;
        ConnectionPool connectionPool = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            CarDAO carDAO = new CarDAO();
            List<CarCategory> categoryList = new ArrayList<>(carDAO.getCarCategory(connection));
            request.setAttribute(CAR_CATEGORY_LIST, categoryList);
        } catch (Exception e) {
            LOG.error("Exception in ShowCategoryPrice has happened. Can not get car list from DB. ", e);
            ShowAllCarsAction action = new ShowAllCarsAction();
            return action.execute(request, response);
        } finally {
            connectionPool.freeConnection(connection);
        }
        return forward;
    }
}