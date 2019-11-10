package com.epam.action;

import com.epam.Path;
import com.epam.dao.impl.CarDAO;
import com.epam.entity.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.epam.action.ConstantField.*;

public class AddCarAction implements IAction {
    private static final Logger LOG = Logger.getLogger(AddCarAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("AddCarAction execute starts.");
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
        double engineVolume = Double.parseDouble(request.getParameter(CAR_ENGINE_VOLUME));
        int baggage = Integer.parseInt(request.getParameter(CAR_BAGGAGE_AMOUNT));
        int seat = Integer.parseInt(request.getParameter(CAR_SEAT_AMOUNT));
        double fuelConsumption = Double.parseDouble(request.getParameter(CAR_FUEL_CONSUMPTION));
        int door = Integer.parseInt(request.getParameter(CAR_DOOR_AMOUNT));
        String year = request.getParameter(CAR_PRODUCTION_YEAR);
        int mileage = Integer.parseInt(request.getParameter(MILEAGE));
        String carImage = request.getParameter(CAR_IMAGE);
        carImage = CAR_IMAGE_FOLDER.concat(carImage).concat(CAR_IMAGE_FORMAT);
        CarDAO carDAO = new CarDAO();
        Map<String, Integer> carDetails = new HashMap<String, Integer>(carDAO.getCarDetails());
        Car car = new Car();
        car.setRegisteredNumber(registeredNumber);
        Brand carBrand = new Brand();
        Model carModel = new Model();
        Color carColor = new Color();
        CarCategory carCategory = new CarCategory();
        Transmission carTransmission = new Transmission();
        Body carBody = new Body();
        EngineType carEngine = new EngineType();
        for (Map.Entry<String, Integer> element : carDetails.entrySet()) {
            String key = element.getKey().toLowerCase();
            Integer value = element.getValue();
            if (key.equals(brand.toLowerCase())) {
                carBrand.setId(value);
            } else if (key.equals(model.toLowerCase())) {
                carModel.setId(value);
            } else if (key.equals(color.toLowerCase())) {
                carColor.setId(value);
            } else if (key.equals(category.toLowerCase())) {
                carCategory.setId(value);
            } else if (key.equals(transmission.toLowerCase())) {
                carTransmission.setId(value);
            } else if (key.equals(body.toLowerCase())) {
                carBody.setId(value);
            } else if (key.equals(engine.toLowerCase())) {
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
        carDAO.insert(car);
        return Path.HOME_PAGE;
    }
}