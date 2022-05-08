package com.util_servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.CartDAO;
import com.model.Cart;
import com.model.User;

@WebServlet(name = "BuyCartProducts", urlPatterns = {"/buy-cart-products"})
public class BuyCartProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BuyCartProducts() {
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
			List<Cart> cartItems = CartDAO.getCart(user.getEmail());
			// cart has no items send to cart page
			if(cartItems == null) {
				response.sendRedirect("cart");
			}else {
				// forward request to buy now
				request.setAttribute("cart", cartItems);
				request.setAttribute("formAction", "create-cart-order-summary");
				getServletContext().getRequestDispatcher("/buy-now.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
