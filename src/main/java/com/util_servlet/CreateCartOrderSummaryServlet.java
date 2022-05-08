package com.util_servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.AddressDAO;
import com.database.CartDAO;
import com.database.OrderItemDAO;
import com.model.Address;
import com.model.Cart;
import com.model.OrderItem;
import com.model.User;

@WebServlet(name = "CreateCartOrderSummaryServlet", urlPatterns = {"/create-cart-order-summary"})
public class CreateCartOrderSummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateCartOrderSummaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get session and user 
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		try {
			// get cart data for corresponding user
			List<Cart> cartItems = CartDAO.getCart(user.getEmail());
			
			// create a LocalDateTime object and format it
			DateTimeFormatter dTformat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime orderDate = LocalDateTime.now();
			
			// convert cart item to order item
			List<OrderItem> orders = cartItems.stream()
					.map(item -> new OrderItem(user.getEmail(), dTformat.format(orderDate), item.getProductId(), item.getQty()))
					.collect(Collectors.toList());
			
			// insert each order in database
			for(OrderItem order : orders) {
				OrderItemDAO.insertOrder(order);
			}
			
			// remove all cart items for corresponding user
			CartDAO.removeFromCart(user.getEmail());
			
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
			session.setAttribute("orders", orders);
			session.setAttribute("orderDateTime", dTformat.format(orderDate));
			response.sendRedirect("order-summary");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
