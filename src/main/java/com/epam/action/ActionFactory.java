package com.epam.action;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionFactory {
    private static final Logger LOG = Logger.getLogger(ActionFactory.class);
    private static final String EMPTY_ACTION = "emptyAction";
    private static Map<String, IAction> actionMap = new ConcurrentHashMap<>();

    static {
        actionMap.put("login", new LoginAction());
        actionMap.put("logout", new LogoutAction());
        actionMap.put("emptyAction", new EmptyAction());
        actionMap.put("register", new RegisterAction());
        actionMap.put("allCars", new ShowAllCarsAction());
        actionMap.put("availableCars", new ShowAvailableCarsAction());
        actionMap.put("orderForm", new OrderFormAction());
        actionMap.put("orderCar", new OrderCarAction());
        actionMap.put("userOrders", new ShowUserOrdersAction());
        actionMap.put("orderPayment", new OrderPaymentAction());
        actionMap.put("RU", new LanguageAction());
        actionMap.put("EN", new LanguageAction());
        actionMap.put("showAllOrders", new ShowAllAdminOrdersAction());
        actionMap.put("orderDetails", new ShowOrderDetailsAction());
        actionMap.put("orderConfirm", new ConfirmOrderAction());
        actionMap.put("orderDecline", new DeclineOrderAction());
        actionMap.put("orderArchive", new ArchiveOrderAction());
        actionMap.put("addCar", new AddCarAction());
        actionMap.put("addCarForm", new AddCarFormAction());
        actionMap.put("changeCarImage", new UpdateCarImageAction());
        actionMap.put("updateCarImageForm", new UpdateCarImageFormAction());
        actionMap.put("updateCar", new UpdateCarAction());
        actionMap.put("updateCarForm", new UpdateCarFormAction());
        actionMap.put("orderPayForm", new OrderPaymentFormAction());
        actionMap.put("showSignInPage", new ShowSignInPageAction());
        actionMap.put("showSignUpPage", new ShowSignUpPageAction());
        actionMap.put("changeCategoryPrice", new ChangeCategoryPriceAction());
        actionMap.put("showCategoryPrice", new ShowCategoryPriceAction());
        LOG.debug("ActionFactory has successfully started.");
        LOG.trace("Actions count: " + actionMap.size());
    }

    public static IAction getAction(String action) {
        if (action == null || !actionMap.containsKey(action)) {
            LOG.trace("This action not found: " + action);
            return actionMap.get(EMPTY_ACTION);
        }
        return actionMap.get(action);
    }
}