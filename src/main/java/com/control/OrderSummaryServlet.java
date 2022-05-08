package com.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.OrderItem;

@WebServlet(name = "OrderSummaryServlet", urlPatterns = {"/order-summary"})
public class OrderSummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrderSummaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<OrderItem> orders = (List<OrderItem>) session.getAttribute("orders");
		String orderDateTime = (String) session.getAttribute("orderDateTime");
		
		request.setAttribute("orders", orders);
		request.setAttribute("orderDateTime", orderDateTime);
		
		session.removeAttribute("orders");
		session.removeAttribute("orderDateTime");
		getServletContext().getRequestDispatcher("/order-summary.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
