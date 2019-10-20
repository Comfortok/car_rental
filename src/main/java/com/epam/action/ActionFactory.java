package com.epam.action;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionFactory {
    private static final Logger LOG = Logger.getLogger(ActionFactory.class);
    private static Map<String, Action> actionMap = new ConcurrentHashMap<>();

    static {
        //commons
        actionMap.put("login", new LoginAction());
        actionMap.put("logout", new LogoutAction());
        actionMap.put("emptyAction", new EmptyAction());
        actionMap.put("register", new RegisterAction());
        actionMap.put("cars", new ShowAllCarsAction());
        actionMap.put("availableCars", new ShowAvailableCarsAction());
        actionMap.put("order", new OrderAction());
        actionMap.put("orderCar", new OrderCarAction());
        actionMap.put("basket", new ShowUnpaidOrdersAction());
        actionMap.put("payAnOrder", new PaymentAction());
        actionMap.put("RU", new LanguageAction());
        actionMap.put("EN", new LanguageAction());

        //...
        System.out.println("AF started, size: " + actionMap.size());
        LOG.debug("ActionFactory has successfully started.");
        LOG.trace("Actions count: " + actionMap.size());
    }

    public static Action getAction(String action) {
        if (action == null || !actionMap.containsKey(action)) {
            LOG.trace("This action not found: " + action);
            return actionMap.get("emptyAction");
        }
        return actionMap.get(action);
    }
}
