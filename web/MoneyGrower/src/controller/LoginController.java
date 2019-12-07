package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import service.LoginService;
import service.TransactionService;

@SuppressWarnings("serial")
public class LoginController extends HttpServlet {

	private LoginService loginService = new LoginService();

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			var username = request.getParameter("username");
			var password = request.getParameter("password");
			var isCorrectUser = loginService.checkCorrectUser(username, password);

			if (isCorrectUser) {
				var user = loginService.getUserByUsername(username);
				var transactionService = new TransactionService();
				var transactionList = transactionService.getTransactionsOfUser(user.getUserID());
				request.setAttribute("transactions", transactionList);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				request.setAttribute("isFailLogin", true);
				request.getRequestDispatcher("login.jsp").include(request, response);
			}
		} catch (Exception e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}
}