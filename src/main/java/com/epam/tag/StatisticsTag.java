package com.epam.tag;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class StatisticsTag extends TagSupport implements Serializable{

    private String command;

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public int doStartTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<CENTER><form name =\"menu\" class=\"tag\" method=\"POST\" action=\"mainServlet\" >\n"
                    + "                    <input type=\"hidden\" name=\"command\" value=\"" + command + "\" />\n"
                    + "                    <input type=\"submit\" value=\"");
        } catch (IOException ex) {
            throw new JspTagException(ex.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspTagException {
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspTagException {
        try {
            pageContext.getOut().write("\"/>\n</form></center><br/>");
        } catch (IOException ex) {
            throw new JspTagException(ex.getMessage());
        }
        return EVAL_PAGE;
    }
}