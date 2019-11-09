package com.epam.action;

import com.epam.Path;
import com.epam.dao.impl.CarDaoImpl;
import com.epam.entity.Car;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static com.epam.action.ConstantField.*;

public class AddCarFormAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        CarDaoImpl carDaoImpl = new CarDaoImpl();
        List<Car> carList = new ArrayList<>(carDaoImpl.getAll());
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
        return Path.ADMIN_ADD_CAR_PAGE;
    }
}