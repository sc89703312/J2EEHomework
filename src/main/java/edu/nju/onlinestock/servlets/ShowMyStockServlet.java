package main.java.edu.nju.onlinestock.servlets;

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

import main.java.edu.nju.onlinestock.beans.ResultListBean;
import main.java.edu.nju.onlinestock.factory.ServiceFactory;
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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowMyStockServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {

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

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);

		if (session == null) {
			//如果没有会话,就返回登录界面去创建会话
			resp.sendRedirect(req.getContextPath() + "/Login");
		} else {

			String loginValue = (String) session.getAttribute("studentId");
			getResultList(req, resp, loginValue);
			displayMyStocklistPage(req, resp);

		}

	}

	public void getResultList(HttpServletRequest req, HttpServletResponse res, String studentId) throws ServletException, IOException {

		List<Result> resultList = ServiceFactory.getResultService().
									getStudentResultList(studentId);

		ResultListBean resultListBean = new ResultListBean();
		resultListBean.setStudentResultList(resultList);

		HttpSession session = req.getSession(false);
		session.setAttribute("list", resultListBean);

	}

	public void displayMyStocklistPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        RequestDispatcher dispatcher
                =req.getRequestDispatcher("/result/resultList.jsp");
        if (dispatcher!= null){
            try {
                dispatcher.forward(req,res);
            }catch (Exception e){
            }
        }
	}

}
