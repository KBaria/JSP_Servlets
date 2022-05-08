package com.util_servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Cart;
import com.model.User;

@WebServlet(name = "BuyNowServlet", urlPatterns = {"/buy-now"})
public class BuyNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BuyNowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get product id
		int productId = Integer.parseInt(request.getParameter("productId"));
		
		// get session and user object
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		// check for user
		if(user != null) {
			// create new cart item and add to the list
			Cart cartItem = new Cart(user.getEmail(), productId, 1);
			List<Cart> cart = Arrays.asList(cartItem);
			
			// attach list to request and forward to buy-now.jsp
			request.setAttribute("cart", cart);
			request.setAttribute("formAction", "create-order-summary");
			getServletContext().getRequestDispatcher("/buy-now.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
