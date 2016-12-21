package main.java.edu.nju.onlinestock.servlets;

import com.sun.deploy.net.HttpRequest;
import main.java.edu.nju.onlinestock.model.Result;
import main.java.edu.nju.onlinestock.utils.JDBCConnector;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private DataSource dataSource = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(){
        dataSource = JDBCConnector.getDataSourceInstance();
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

        PrintWriter pw = response.getWriter();

        RequestDispatcher dispatcher
                =request.getRequestDispatcher("/user/login.html");
        if (dispatcher!= null)
            dispatcher.include(request,response);


        ServletContext Context= getServletContext();
        int visitorCounter= (int) Context.getAttribute("visitorCounter");
        int webCounter = (int) Context.getAttribute("webCounter");

        pw.println("<p>The number of logged in is: "+webCounter+"</p>");
        pw.println("<p>The number of visitors is: "+visitorCounter+"</p>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        String studentId = request.getParameter("studentId");
        verifyStudent(request, response, studentId);
    }

    private void verifyStudent(HttpServletRequest req, HttpServletResponse resp, String studentId){

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList list = new ArrayList();
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt = connection.prepareStatement("select * from student where id=?");
            stmt.setString(1, studentId);
            result = stmt.executeQuery();
            if(result.next()){
                result.close();
                stmt.close();
                connection.close();

                //登录成功后增加在线登录人数
                HttpSession session = req.getSession(true);
                session.setAttribute("studentId", studentId);
                session.setAttribute("visitor", false);
                session.setMaxInactiveInterval(3600);

                resp.sendRedirect(req.getContextPath()+"/ShowMyStockServlet");
            }else{
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "User Id not Found");
                result.close();
                stmt.close();
                connection.close();
                return;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
