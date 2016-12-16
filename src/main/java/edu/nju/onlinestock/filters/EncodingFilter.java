package main.java.edu.nju.onlinestock.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns="/*", filterName="EncodingFilter",
        initParams={
                @WebInitParam( name = "encoding", value="utf-8"),
        }
)
public class EncodingFilter implements Filter{

    private String encoding = null;

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //System.out.println("before encoding " + encoding + " filter！");
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset="+encoding);
        chain.doFilter(request, response);
        //System.out.println("after encoding " + encoding + " filter！");
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
        encoding = fConfig.getInitParameter("encoding");
        if (null == encoding) {
            throw new ServletException("EncodingFilter named " + fConfig.getFilterName() +" missing encoding init parameter.");
        }
    }
}
