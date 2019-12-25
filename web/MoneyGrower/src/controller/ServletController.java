package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import service.LoginService;
import service.TransactionService;

@SuppressWarnings("serial")
public class ServletController extends HttpServlet {

	private TransactionService transactionService = new TransactionService();
	private LoginService loginService = new LoginService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		String action = request.getServletPath();
		switch (action) {
		case "/view":
			viewTransaction(request, response);
			break;
		}
	}
	
	private void viewTransaction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			var username = request.getParameter("username");
			var password = request.getParameter("password");
			var isCorrectUser = loginService.checkCorrectUser(username, password);

			if (isCorrectUser) {
				var user = loginService.getUserByUsername(username);
				var transactionList = transactionService.getTransactionsOfUser(user.getUserID());
				request.setAttribute("user", user);
				request.setAttribute("transactionsMap", transactionList);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				request.setAttribute("isFailLogin", true);
				request.getRequestDispatcher("login.jsp").include(request, response);
			}
		} catch (Exception e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}