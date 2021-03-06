package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Order;
import be.vdab.services.OrderService;

@WebServlet("/orderdetail.htm")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/orderdetail.jsp";
	private final transient OrderService orderService = new OrderService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// GET ORDER TO SHOW FROM PARAMETER
		if (request.getParameter("id") != null) {
			try {
				long selectedOrder = Long.parseLong(request.getParameter("id"));
				// READ ORDER FROM DATABASE
				Order order = orderService.read(selectedOrder);
				request.setAttribute("order", order);
			} catch (NumberFormatException ex) {
				request.setAttribute("fout", "Order id is not correct");
			}
		} else {
			request.setAttribute("fout", "No order selected");
		}
		// GET ON WITH IT
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}