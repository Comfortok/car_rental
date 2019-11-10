package com.epam.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class EncodingFilter implements Filter {
    private final String ENCODING = "encoding";
    private String code;
    private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOG.debug("EncodingFilter.init()");
        code = filterConfig.getInitParameter(ENCODING);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        LOG.debug("EncodingFilter.doFilter()");
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        LOG.debug("EncodingFilter.destroy()");
        code = null;
    }
}