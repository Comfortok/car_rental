package com.epam.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {
    private static final Logger LOG = Logger.getLogger(RequestListener.class);
    private static int requestCount;

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        LOG.debug("RequestListener initialized");
        ServletContext context = sre.getServletContext();
        ServletRequest request = sre.getServletRequest();
        synchronized (context) {
            String name = ((HttpServletRequest) request).getRequestURI();
            context.log("Request for " + name + "; Count: " + ++requestCount);
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        LOG.debug("RequestListener destroyed");
    }
}