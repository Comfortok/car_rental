package com.epam.action;

import com.epam.dao.impl.CarDAO;
import com.epam.entity.CarCategory;
import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

import static com.epam.constant.ConstantField.*;

public class ChangeCategoryPriceAction implements IAction {
    private static final Logger LOG = Logger.getLogger(ChangeCategoryPriceAction.class);
    private static final int ZERO = 0;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String stringPrice = request.getParameter(PRICE);
        System.out.println("strPrice " + stringPrice);
        if (stringPrice.isEmpty() || Integer.parseInt(stringPrice) == ZERO) {
            request.setAttribute(EMPTY_FIELD_ERROR, EMPTY_FIELD_ERROR_MESSAGE);
            ShowCategoryPriceAction action = new ShowCategoryPriceAction();
            return action.execute(request, response);
        } else {
            double price = Double.parseDouble(stringPrice);
            System.out.println("price " + price);
            long categoryId = Long.parseLong(request.getParameter(CATEGORY_ID));
            System.out.println("catId " + categoryId);
            CarDAO carDAO = new CarDAO();
            CarCategory category = new CarCategory();
            category.setId(categoryId);
            category.setPricePerDay(price);
            ConnectionPool connectionPool = null;
            Connection connection = null;
            try {
                connectionPool = ConnectionPool.getInstance();
                connection = connectionPool.getConnection();
                System.out.println("before update");
                carDAO.updatePrice(category, connection);
                System.out.println("after");
            } catch (Exception e) {
                System.out.println("catch");
                LOG.error("Exception in ChangeCategoryPriceAction has happened. Can not update category price. ", e);
                ShowAllCarsAction action = new ShowAllCarsAction();
                return action.execute(request, response);
            } finally {
                connectionPool.freeConnection(connection);
            }
        }
        ShowCategoryPriceAction action = new ShowCategoryPriceAction();
        return action.execute(request, response);
    }
}