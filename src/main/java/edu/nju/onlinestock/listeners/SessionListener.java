package main.java.edu.nju.onlinestock.listeners;

/**
 * Created by echo on 16/12/20.
 */

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;


@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener,ServletContextListener {

    private ServletContext servletContext = null;


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.servletContext = servletContextEvent.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

        System.out.println("session attribute added");


        int visitorCounter = (int) servletContext.getAttribute("visitorCounter");
        int webCounter = (int) servletContext.getAttribute("webCounter");


        HttpSession session = httpSessionBindingEvent.getSession();

        System.out.println(session.getAttribute("studentId")+" !!");

        if(session.getAttribute("studentId")!=null){
            servletContext.setAttribute("webCounter", (webCounter+1));
        }

        if( session.getAttribute("visitor") != null){
            servletContext.setAttribute("visitorCounter", (visitorCounter+1));
        }

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        int visitorCounter= (int) servletContext.getAttribute("visitorCounter");
        int webCounter= (int) servletContext.getAttribute("webCounter");


        HttpSession session = httpSessionEvent.getSession();

        if(session.getAttribute("studentId")!=null){
            servletContext.setAttribute("webCounter", (webCounter-1));
        }

        if(session.getAttribute("visitor")!=null){
            servletContext.setAttribute("visitorCounter", (visitorCounter-1));
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

}
