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
	private static final String REDIRECT_URL = "%s/index.htm";
	private final transient OrderService orderService = new OrderService();
	private static final int AANTAL_RIJEN = 10;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// RETRIEVE noStockUnshippedOrders IDS FROM SESSION
		@SuppressWarnings("unchecked")
		List<Long> noStockUnshippedOrdersIds = (List<Long>) request.getSession().getAttribute("noStockUnshippedOrders");
		request.getSession().removeAttribute("noStockUnshippedOrders"); // don't need this anymore
		
		// RETRIEVE shippedOrders IDS FROM SESSION
		@SuppressWarnings("unchecked")
		List<Long> shippedOrdersIds = (List<Long>) request.getSession().getAttribute("shippedOrders");
		request.getSession().removeAttribute("shippedOrders"); // don't need this anymore

		// PUT noStockUnshippedOrders OBJECTS IN ATTRIBUTE
		if (noStockUnshippedOrdersIds != null) {
			List<Order> noStockUnshippedOrders = new ArrayList<>();
			for (long id : noStockUnshippedOrdersIds) {
				noStockUnshippedOrders.add(orderService.read(id));
			}
			request.setAttribute("noStockUnshippedOrders", noStockUnshippedOrders);
		}
		
		// PUT shippedOrders ID's IN ATTRIBUTE
		if (shippedOrdersIds != null) {
			request.setAttribute("shippedOrdersIds", shippedOrdersIds);
		}

		// PUT ALL unshippedorders IN ATTRIBUTE
		getUnshippedOrders(request, response);

		// GET ON WITH IT
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		// GET ORDERS TO SHIP FROM PARAMETER
		if (request.getParameterValues("ship") != null) {
			List<Long> noStockUnshippedOrders = new ArrayList<>();
			List<Long> shippedOrders = new ArrayList<>();
			// SHIP ALL ORDERS
			for (String orderId : request.getParameterValues("ship")) {
				try {
					Long id = Long.parseLong(orderId);
					// IF ORDER NOT SHIPPED, ADD TO LIST OF UNSHIPPED ORDERS (NO STOCK)
					if (!orderService.setAsShipped(id)) {
						noStockUnshippedOrders.add(id);
					}else{
						shippedOrders.add(id);
					}
				} catch (NumberFormatException e) {
					// IF NO LONG DO NOTHING
				}
			}
			request.getSession().setAttribute("noStockUnshippedOrders", noStockUnshippedOrders);
			request.getSession().setAttribute("shippedOrders", shippedOrders);
		}
		// GET ON WITH IT
		response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
	}

	// FUNCTIONAL METHODS

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
