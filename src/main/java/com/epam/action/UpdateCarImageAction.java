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

import static com.epam.constant.ConstantField.*;

public class UpdateCarImageAction implements IAction {
    private static final Logger LOG = Logger.getLogger(UpdateCarImageAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("UpdateCarImageAction execute starts.");
        String image = (String) request.getAttribute(CAR_IMAGE);
        long carId = Long.parseLong((String) request.getAttribute(CAR_ID));
        String forward;
        if (image.isEmpty()) {
            request.setAttribute(EMPTY_FIELD_ERROR, EMPTY_FIELD_ERROR_MESSAGE);
            request.setAttribute(CAR_ID, carId);
            UpdateCarImageFormAction updateCarImageFormAction = new UpdateCarImageFormAction();
            forward = updateCarImageFormAction.execute(request, response);
        } else {
            ConnectionPool connectionPool = null;
            Connection connection = null;
            try {
                connectionPool = ConnectionPool.getInstance();
                connection = connectionPool.getConnection();
                image = CAR_IMAGE_FOLDER.concat(image);
                Car car = new Car();
                car.setId(carId);
                car.setImageName(image);
                CarDAO carDao = new CarDAO();
                carDao.updateImage(car, connection);
                ShowAllCarsAction showAllCarsAction = new ShowAllCarsAction();
                forward = showAllCarsAction.execute(request, response);
            } catch (SQLException e) {
                LOG.error("Exception in ConfirmOrderAction has happened. Can not update an order. ", e);
                return JspPagePath.ERROR_PAGE;
            } finally {
                connectionPool.freeConnection(connection);
            }
        }
        return forward;
    }
}