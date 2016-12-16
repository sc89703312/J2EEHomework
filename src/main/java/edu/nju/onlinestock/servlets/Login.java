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


        //获取上下文环境
		ServletContext Context= getServletContext();

        //获取上下文环境中的webCounter数量
        int webCounter= Integer.parseInt((String) Context.getAttribute("webCounter"));

        //如果不是登出跳转到这张界面 就让webCounter数量自增 重新写回
        if (null == request.getParameter("Logout")) {
			System.out.println("pageCounter++\n");
			webCounter++;
			Context.setAttribute("webCounter", Integer.toString(webCounter));
		}

        //取出cookie中存放的LoginCookie的用户登陆名称
		String login="";
		HttpSession session = request.getSession(false);
		Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        		
        if (null != cookies) {
            // Look through all the cookies and see if the
            // cookie with the login info is there.
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals("LoginCookie")) {
                    login=cookie.getValue();
                    break;
                }
            }
        }
    
        // 如果是通过登出跳转到的这张界面 那么让会话失效
        if (null != request.getParameter("Logout")) {
            if (null != session) {
            	session.invalidate();
                session = null;
            }
        }


        PrintWriter pw = response.getWriter();
        RequestDispatcher dispatcher
                =request.getRequestDispatcher("/user/login.html");
        if (dispatcher!= null)
            dispatcher.include(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        String studentId = request.getParameter("studentId");
        HttpSession session = request.getSession(true);
        session.setAttribute("studentId", studentId);
        session.setMaxInactiveInterval(3600);

        //response.sendRedirect(request.getContextPath()+"/ShowMyStockServlet");

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
