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
import java.util.ArrayList;


@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener,ServletContextListener {

    private ServletContext servletContext = null;
    ArrayList loggedInList = new ArrayList<Integer>();
    ArrayList visitorList = new ArrayList<String>();

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

        if(session.getAttribute("visitor")!=null){
            if( (Boolean)session.getAttribute("visitor") ){
                if(!visitorList.contains(session.getAttribute("uuid"))){
                    System.out.println("visitor++");
                    servletContext.setAttribute("visitorCounter", (visitorCounter+1));
                    visitorList.add(session.getAttribute("uuid"));
                }
            }else{
                if(!loggedInList.contains(session.getAttribute("studentId"))){
                    System.out.println("logged in ++");
                    servletContext.setAttribute("webCounter", (webCounter+1));
                    loggedInList.add(session.getAttribute("studentId"));
                }
            }
        }


    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        int visitorCounter= (int) servletContext.getAttribute("visitorCounter");
        int webCounter= (int) servletContext.getAttribute("webCounter");


        HttpSession session = httpSessionEvent.getSession();

        if((Boolean) session.getAttribute("visitor")){
            servletContext.setAttribute("visitorCounter", (visitorCounter-1));
            System.out.println("visitor --");
            visitorList.remove(session.getAttribute("uuid"));
        }else{
            servletContext.setAttribute("webCounter", (webCounter-1));
            System.out.println("logged in --");
            loggedInList.remove(session.getAttribute("studentId"));
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
