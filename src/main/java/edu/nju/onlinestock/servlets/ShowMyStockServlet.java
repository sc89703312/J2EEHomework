package main.java.edu.nju.onlinestock.servlets;

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

import main.java.edu.nju.onlinestock.model.Result;
import main.java.edu.nju.onlinestock.model.Stock;
import main.java.edu.nju.onlinestock.model.Student;
import main.java.edu.nju.onlinestock.utils.JDBCConnector;


/**
 * Servlet implementation class StockListServlet
 */
@WebServlet("/ShowMyStockServlet")
public class ShowMyStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowMyStockServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
        dataSource = JDBCConnector.getDataSourceInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession(false);

		if (session == null) {
			//如果没有会话,就返回登录界面去创建会话
			resp.sendRedirect(req.getContextPath() + "/Login");
		} else {

			String loginValue = (String) session.getAttribute("studentId");
			System.out.println(loginValue + " session");

			req.setAttribute("studentId", loginValue);
			getStockList(req, resp);
			displayMyStocklistPage(req, resp);
			displayLogoutPage(req, resp);

		}

	}

	public void getStockList(HttpServletRequest req, HttpServletResponse res) {

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
			stmt = connection.prepareStatement("select * from result where student_id=?");
			stmt.setString(1, (String) req.getAttribute("studentId"));
			result = stmt.executeQuery();
			while (result.next()) {
                Result examResult = new Result();
                examResult.setId(result.getInt("id"));
                examResult.setStudent_id(result.getInt("student_id"));
                examResult.setExam_id(result.getInt("exam_id"));
                examResult.setResult(result.getInt("result"));
				list.add(examResult);
			}

            result.close();
            stmt.close();
            connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		req.setAttribute("list", list);

	}

	public void displayLogoutPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ServletContext Context= getServletContext();
		int visitorCounter= (int) Context.getAttribute("visitorCounter");
		int webCounter = (int) Context.getAttribute("webCounter");
		
		PrintWriter out = res.getWriter();
		out.println("<form method='GET' action='" + res.encodeURL(req.getContextPath() + "/Login") + "'>");
		out.println("</p>");
		out.println("<input type='submit' name='Logout' value='Logout'>");
		out.println("</form>");
		out.println("</p>Now the number of logged in is: " + webCounter);
        out.println("</p>Now the number of visitor is: " + visitorCounter);
		out.println("</body></html>");

	}

	public void displayMyStocklistPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ArrayList list = (ArrayList) req.getAttribute("list"); // resp.sendRedirect(req.getContextPath()+"/MyStockList");

		PrintWriter out = res.getWriter();

        RequestDispatcher dispatcher
                =req.getRequestDispatcher("/user/result.html");
        if (dispatcher!= null){
            try {
                dispatcher.include(req,res);
            }catch (Exception e){

            }
        }

		out.println("<p>Welcome " + req.getAttribute("studentId") + "</p>");

		out.println("我的考试记录:  ");
		System.out.println("stocklist");
        out.println("<ul>");
		for (int i = 0; i < list.size(); i++) {
			Result result = (Result) list.get(i);
            System.out.println(result.getResult());
            if(result.getResult() != -1){
                out.println("<li>"+result.getExam_id()+" : "+result.getResult()+"</li>");
            }else{
                out.println("<li style=\"color: red\">"+result.getExam_id()+" : "+"此次考试未参加!"+"</li>");
            }

		}
        out.println("</ul>");
		out.println("</p>");
		out.println("Click <a href='" + res.encodeURL(req.getRequestURI()) + "'>here</a> to reload this page.<br>");
	}

}
