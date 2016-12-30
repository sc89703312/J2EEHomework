package main.java.edu.nju.onlinestock.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by echo on 16/12/30.
 */
public class WebCounterHandler extends TagSupport {
    public int doEndTag() throws JspException {

        JspWriter out = pageContext.getOut();
        int webCounter = (int)pageContext.getServletContext().getAttribute("webCounter");
        int visitorCounter = (int)pageContext.getServletContext().getAttribute("visitorCounter");

        try{
            out.println("<p>The number of logged in is: "+webCounter+"</p>");
            out.println("<p>The number of visitors is: "+visitorCounter+"</p>");
        }catch (Exception e){

        }

        return EVAL_PAGE;
    }
}
