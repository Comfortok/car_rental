package com.epam.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentDateTagHandler extends TagSupport {
    private static final Logger LOG = Logger.getLogger(CurrentDateTagHandler.class);
    private final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public int doStartTag() {
        LOG.debug("CurrentDateTagHandler starts");
        JspWriter out = pageContext.getOut();
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        try {
            out.print(format.format(Calendar.getInstance().getTime()));
        } catch (IOException e) {
            LOG.error("Exception in CurrentDateTagHandler has happened. " , e);
        }
        LOG.debug("CurrentDateTagHandler ends");
        return SKIP_BODY;
    }
}