/*
package com.epam.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class StatisticsTagHandler {

    OrderDao orderDao;
    CarDao carDao;
    private static List list;
    private static final Logger LOG = Logger.getLogger(StatisticsTagHandler.class);

    */
/**
     *
     * @return list of all users reqistered in the system, barring admin (to
     * admin)
     *//*

    public static List getUserStats() {
        ClientDao clientDao = (ClientDao) DaoFactory.getDao(DaoType.CLIENT);
        try {
            list = (ArrayList) clientDao.getAllUsers();
        } catch (DAOException ex) {
            LOG.error("DAOException while StatisticsTagHandler" + ex);
        }
        return list;
    }
    
    */
/**
     *
     * @param id of the user requesting information
     * @return list of all orders which user has to pay for
     *//*

    public static List getUsersOrders(int id) {
        OrderDao orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
        try {
            list = (ArrayList) orderDao.getUByUserId(id);
        } catch (DAOException ex) {
            LOG.error("DAOException while StatisticsTagHandler" + ex);
        }
        return list;
    }

    */
/**
     *
     * @return all new orders (to admin)
     *//*

    public static List getOrderStats() {
        OrderDao orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
        try {
            list = (ArrayList) orderDao.getNewOrders();
        } catch (DAOException ex) {
            LOG.error("DAOException while StatisticsTagHandler" + ex);
        }
        return list;
    }

    */
/**
     *
     * @return all cars in the system (to admin)
     *//*

    public static List getCarsStats() {
        CarDao carDao = (CarDao) DaoFactory.getDao(DaoType.CAR);
        try {
            list = (ArrayList) carDao.getAll();
        } catch (DAOException ex) {
            LOG.error("DAOException while StatisticsTagHandler" + ex);
        }
        return list;
    }
}
*/
