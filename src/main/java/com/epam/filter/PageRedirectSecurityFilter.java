package com.epam.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageRedirectSecurityFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(PageRedirectSecurityFilter.class);
    private String indexPath;

    @Override
    public void init(FilterConfig filterConfig) {
        indexPath = filterConfig.getInitParameter("INDEX_PATH");
        LOG.debug("PageRedirectSecurityFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.debug("doFilter() starts in PageRedirectSecurityFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + indexPath);
        chain.doFilter(request, response);
        LOG.debug("doFilter() ends in PageRedirectSecurityFilter");
    }

    @Override
    public void destroy() {
        indexPath = null;
        LOG.debug("PageRedirectSecurityFilter destroyed");
    }
}