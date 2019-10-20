
package com.epam.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.epam.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // todo some actions

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //ConnectionPool.getInstance().freeConnection(new Connection());
        LOG.info("ConnectionPool is released");
    }
}