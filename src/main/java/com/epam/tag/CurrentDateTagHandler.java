package com.epam.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentDateTagHandler extends TagSupport {
    private final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        try {
            out.print(format.format(Calendar.getInstance().getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}