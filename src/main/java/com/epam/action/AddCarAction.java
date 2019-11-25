package com.epam.action;

import com.epam.constant.JspPagePath;
import com.epam.dao.impl.CarDAO;
import com.epam.entity.*;
import com.epam.pool.ConnectionPool;
import com.epam.util.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static com.epam.constant.ConstantField.*;

public class AddCarAction implements IAction {
    private static final Logger LOG = Logger.getLogger(AddCarAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String registeredNumber = request.getParameter(CAR_REGISTERED_NUMBER);
        String brand = request.getParameter(CAR_BRAND);
        String model = request.getParameter(CAR_MODEL);
        String color = request.getParameter(CAR_COLOR);
        String category = request.getParameter(CAR_CATEGORY);
        String transmission = request.getParameter(CAR_TRANSMISSION);
        String body = request.getParameter(CAR_BODY);
        String engine = request.getParameter(CAR_ENGINE);
        String airConditioner = request.getParameter(CAR_AC);
        String available = request.getParameter(CAR_AVAILABILITY);
        String stringEngineVolume = request.getParameter(CAR_ENGINE_VOLUME);
        String stringBaggage = request.getParameter(CAR_BAGGAGE_AMOUNT);
        String stringSeat = request.getParameter(CAR_SEAT_AMOUNT);
        String stringFuel = request.getParameter(CAR_FUEL_CONSUMPTION);
        String stringDoor = request.getParameter(CAR_DOOR_AMOUNT);
        String year = request.getParameter(CAR_PRODUCTION_YEAR);
        String stringMileage = request.getParameter(MILEAGE);
        String carImage = request.getParameter(CAR_IMAGE);
        String forward = JspPagePath.HOME_PAGE;
        if (Validator.checkEmptyAttributes(registeredNumber, brand, model, color, category, transmission, body, engine,
                airConditioner, available, stringEngineVolume, stringBaggage, stringSeat, stringFuel, stringDoor,
                year, stringMileage)) {
            request.setAttribute(EMPTY_FIELD_ERROR, EMPTY_FIELD_ERROR_MESSAGE);
            LOG.trace("Validation error in AddCarAction has happened. Can not insert empty fields to DB. ");
            AddCarFormAction addCarFormAction = new AddCarFormAction();
            return addCarFormAction.execute(request, response);
        } else {
            double engineVolume = Double.parseDouble(stringEngineVolume);
            int baggage = Integer.parseInt(stringBaggage);
            int seat = Integer.parseInt(stringSeat);
            double fuelConsumption = Double.parseDouble(stringFuel);
            int door = Integer.parseInt(stringDoor);
            int mileage = Integer.parseInt(stringMileage);
            if (!carImage.isEmpty()) {
                carImage = CAR_IMAGE_FOLDER.concat(carImage).concat(CAR_IMAGE_FORMAT);
            } else {
                carImage = DEFAULT_IMAGE_NAME;
            }
            ConnectionPool connectionPool = null;
            Connection connection = null;
            try {
                connectionPool = ConnectionPool.getInstance();
                connection = connectionPool.getConnection();
                CarDAO carDAO = new CarDAO();
                Car car = new Car();
                car.setRegisteredNumber(registeredNumber);
                Map<String, Integer> carDetails;
                carDetails = new HashMap<>(carDAO.getCarDetails(connection));
                setCarAttribute(car, carDetails, brand, model, color, category, transmission, body, engine);
                car.setHasAirConditioner(airConditioner);
                car.setAvailable(available);
                car.setEngineVolume(engineVolume);
                car.setBaggageAmount(baggage);
                car.setPassengerAmount(seat);
                car.setFuelConsumption(fuelConsumption);
                car.setDoorAmount(door);
                car.setProductionYear(year);
                car.setMileage(mileage);
                car.setImageName(carImage);
                carDAO.insert(car, connection);
            } catch (Exception e) {
                LOG.error("Exception in AddCarAction has happened. Can not insert a new car to DB. ", e);
                return JspPagePath.ERROR_PAGE;
            } finally {
                connectionPool.freeConnection(connection);
            }
        }
        return forward;
    }

    private void setCarAttribute(Car car, Map<String, Integer> carDetails, String brand, String model, String color, String category,
                                 String transmission, String body, String engine) {
        Brand carBrand = new Brand();
        Model carModel = new Model();
        Color carColor = new Color();
        CarCategory carCategory = new CarCategory();
        Transmission carTransmission = new Transmission();
        Body carBody = new Body();
        EngineType carEngine = new EngineType();
        for (Map.Entry<String, Integer> element : carDetails.entrySet()) {
            String key = element.getKey();
            Integer value = element.getValue();
            if (key.equalsIgnoreCase(brand)) {
                carBrand.setId(value);
            } else if (key.equalsIgnoreCase(model)) {
                carModel.setId(value);
            } else if (key.equalsIgnoreCase(color)) {
                carColor.setId(value);
            } else if (key.equalsIgnoreCase(category)) {
                carCategory.setId(value);
            } else if (key.equalsIgnoreCase(transmission)) {
                carTransmission.setId(value);
            } else if (key.equalsIgnoreCase(body)) {
                carBody.setId(value);
            } else if (key.equalsIgnoreCase(engine)) {
                carEngine.setId(value);
            }
        }
        carModel.setBrand(carBrand);
        car.setModel(carModel);
        car.setColor(carColor);
        car.setCategory(carCategory);
        car.setTransmission(carTransmission);
        car.setBody(carBody);
        car.setEngineType(carEngine);
    }
}