package main.java.edu.nju.onlinestock.tag;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by echo on 16/12/30.
 */
public class CheckSessionHandler extends TagSupport {

    public int doEndTag() throws JspException {

        HttpSession session = pageContext.getSession();

        if(session.getAttribute("studentId") == null){
            HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

            try {
                session.invalidate();
                response.sendRedirect("/onlineStockWeb03/Login");
            }catch (Exception e){

            }

            return SKIP_PAGE;
        }else{
            return EVAL_PAGE;
        }

    }
}
