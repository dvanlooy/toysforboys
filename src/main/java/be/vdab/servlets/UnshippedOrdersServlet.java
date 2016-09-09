package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Order;
import be.vdab.services.OrderService;

@WebServlet("/index.htm")
public class UnshippedOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/unshippedorders.jsp";
	private final transient OrderService orderService = new OrderService();
	private static final int AANTAL_RIJEN = 10;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		getUnshippedOrders(request, response);
		

		// GET ON WITH IT
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameterValues("ship") != null) {
//			List<Order> noStockUnshippedOrders = new ArrayList<>();
			List<Long> noStockUnshippedOrders = new ArrayList<>();
			for (String orderId : request.getParameterValues("ship")) {
				try {
					Long id = Long.parseLong(orderId);
					if (!orderService.setAsShipped(id)) {
//						noStockUnshippedOrders.add(orderService.read(id));
						noStockUnshippedOrders.add(id);
					}
				} catch (NumberFormatException e) {
					// IF NO LONG DO NOTHING
				}
			}
			request.setAttribute("noStockUnshippedOrders", noStockUnshippedOrders);
		}
		doGet(request, response);
//		getUnshippedOrders(request, response);
//		// GET ON WITH IT
//		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	private void getUnshippedOrders(HttpServletRequest request, HttpServletResponse response) {
		int vanafRij = request.getParameter("vanafRij") == null ? 0
				: Integer.parseInt(request.getParameter("vanafRij"));
		request.setAttribute("vanafRij", vanafRij);
		request.setAttribute("aantalRijen", AANTAL_RIJEN);
		List<Order> unshippedorders = orderService.findAllUnshippedOrders(vanafRij, AANTAL_RIJEN + 1);
		if (unshippedorders.size() <= AANTAL_RIJEN) {
			request.setAttribute("laatstePagina", true);
		} else {
			unshippedorders.remove(AANTAL_RIJEN);
		}
		// SET UNSHIPPEDORDERS IN ATTRIBUTES
		request.setAttribute("unshippedorders", unshippedorders);
	}

}
