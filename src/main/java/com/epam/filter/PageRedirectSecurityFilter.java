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
        LOG.debug("PageRedirectSecurityFilter.init()");
        indexPath = filterConfig.getInitParameter("INDEX_PATH");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.debug("PageRedirectSecurityFilter.doFilter()");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + indexPath);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOG.debug("PageRedirectSecurityFilter.destroy()");
        indexPath = null;
    }
}