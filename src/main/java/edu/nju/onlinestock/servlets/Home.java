package main.java.edu.nju.onlinestock.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * Created by echo on 16/12/17.
 */
@WebServlet("/Home")
public class Home extends HttpServlet{
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(){}

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        HttpSession session = request.getSession(false);

        PrintWriter pw = response.getWriter();
        RequestDispatcher dispatcher
                =request.getRequestDispatcher("/user/home.html");
        if (dispatcher!= null)
            dispatcher.include(request,response);


        if(session!=null){
            if(request.getParameter("Login")!=null){
                if((Boolean)session.getAttribute("visitor")){
                    session.invalidate();
                    session = null;
                }
                response.sendRedirect(request.getContextPath()+"/Login");
            }

            if(request.getParameter("Exit")!=null){
                session.invalidate();
                return;
            }

        }else{
            session = request.getSession(true);
            session.setAttribute("uuid", UUID.randomUUID().toString());
            session.setAttribute("visitor", true);
            session.setMaxInactiveInterval(3600);
        }

        ServletContext Context= getServletContext();
        int visitorCounter= (int) Context.getAttribute("visitorCounter");
        int webCounter = (int) Context.getAttribute("webCounter");


        pw.println("<p>The number of logged in is: "+webCounter+"</p>");
        pw.println("<p>The number of visitors is: "+visitorCounter+"</p>");

        //System.out.println("The number of logged in is: "+ webCounter);
        //System.out.println("The number of visitors is: "+ visitorCounter);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}
