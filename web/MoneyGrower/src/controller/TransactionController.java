package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import service.TransactionService;

@SuppressWarnings("serial")
public class TransactionController extends HttpServlet {

	private TransactionService transactionService = new TransactionService();

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			var userID = Integer.parseInt(/* request.getParameter("userID") */ "10001");
			var transactionList = transactionService.getTransactionsOfUser(userID);
			request.setAttribute("transactions", transactionList);
			request.getRequestDispatcher("transactions.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}
}