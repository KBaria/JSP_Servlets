package com.util_servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.AddressDAO;
import com.database.OrderItemDAO;
import com.model.Address;
import com.model.OrderItem;
import com.model.User;

@WebServlet(name = "CreateOrderSummaryServlet", urlPatterns = {"/create-order-summary"})
public class CreateOrderSummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateOrderSummaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get product id and quantity
		int id = Integer.parseInt(request.getParameter("id"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		
		// get user from session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		// create a LocalDateTime object and format it
		DateTimeFormatter dTformat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime orderDate = LocalDateTime.now();
		
		// create order item object and add  to database
		OrderItem orderItem = new OrderItem(user.getEmail(), dTformat.format(orderDate), id, qty);
		
		try {
			OrderItemDAO.insertOrder(orderItem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.removeAttribute("orderFlag");
		
		String address = request.getParameter("address");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String pin = request.getParameter("pin");
		
		try {
			Address userAddress = AddressDAO.getAddress(user.getEmail());
			
			if(userAddress == null) {
				AddressDAO.insertAddress(new Address(user.getEmail(), address, state, city, pin));
			}else {
				AddressDAO.updateAddress(new Address(user.getEmail(), address, state, city, pin));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// attach list of order items and date with session and forward to order-summary
		List<OrderItem> orders = Arrays.asList(orderItem);
		session.setAttribute("orders", orders);
		session.setAttribute("orderDateTime", dTformat.format(orderDate));
		response.sendRedirect("order-summary");
	}
}
