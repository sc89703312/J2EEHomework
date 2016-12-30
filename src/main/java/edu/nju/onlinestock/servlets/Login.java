package main.java.edu.nju.onlinestock.servlets;

import com.sun.deploy.net.HttpRequest;
import main.java.edu.nju.onlinestock.factory.DaoFactory;
import main.java.edu.nju.onlinestock.factory.ServiceFactory;
import main.java.edu.nju.onlinestock.model.Result;
import main.java.edu.nju.onlinestock.model.Student;
import main.java.edu.nju.onlinestock.utils.JDBCConnector;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(){
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(false);

        if (null != request.getParameter("Logout")) {

            if (null != session) {
            	session.invalidate();
                session = null;
            }

            response.sendRedirect(request.getContextPath()+"/Login");
        }

        if(session != null){
            response.sendRedirect(request.getContextPath()+"/ShowMyStockServlet");
        }

        RequestDispatcher dispatcher
                =request.getRequestDispatcher("/result/login.jsp");
        if (dispatcher!= null)
            dispatcher.include(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        String studentId = request.getParameter("studentId");
        verifyStudent(request, response, studentId);
    }

    private void verifyStudent(HttpServletRequest req, HttpServletResponse resp, String studentId) throws ServletException, IOException{

        Student loggedStudent = ServiceFactory.getStudentService().findStudentById(studentId);
        if(loggedStudent != null){
            HttpSession session = req.getSession(true);
            session.setAttribute("studentId", studentId);
            session.setAttribute("studentInfo", loggedStudent);
            session.setAttribute("visitor", false);
            session.setMaxInactiveInterval(3600);

            resp.sendRedirect(req.getContextPath()+"/ShowMyStockServlet");
        }else{
            req.setAttribute("studentId",studentId);
            getServletContext().getRequestDispatcher("/result/nonUser.jsp").forward(req, resp);
            return;
        }
    }
}
