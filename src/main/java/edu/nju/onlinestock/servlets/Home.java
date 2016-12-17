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

        ServletContext Context= getServletContext();
        int visitorCounter= Integer.parseInt((String) Context.getAttribute("visitorCounter"));
        int webCounter = Integer.parseInt((String) Context.getAttribute("webCounter"));

        PrintWriter pw = response.getWriter();
        RequestDispatcher dispatcher
                =request.getRequestDispatcher("/user/home.html");
        if (dispatcher!= null)
            dispatcher.include(request,response);

        if(session!=null){
            System.out.println("session not null");
            if(request.getParameter("Login")!=null){
                response.sendRedirect(request.getContextPath()+"/Login");
            }
        }else{

            if(request.getParameter("Login")!=null){
                System.out.println("Login!");
                visitorCounter--;
                Context.setAttribute("visitorCounter", Integer.toString(visitorCounter));
                response.sendRedirect(request.getContextPath()+"/Login");
            }else{
                if(request.getParameter("Exit")!=null){
                    visitorCounter--;
                    Context.setAttribute("visitorCounter", Integer.toString(visitorCounter));
                }else{
                    visitorCounter++;
                    Context.setAttribute("visitorCounter", Integer.toString(visitorCounter));
                }
            }
        }

        pw.println("<p>The number of logged in is: "+webCounter+"</p>");
        pw.println("<p>The number of visitors is: "+visitorCounter+"</p>");

        System.out.println("The number of logged in is: "+ webCounter);
        System.out.println("The number of visitors is: "+ visitorCounter);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}
