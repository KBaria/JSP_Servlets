package com.control;

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

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get session and user object
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		try {
			List<Cart> cartItems = null;
			
			// get cart if user exists
			if(user != null) {
				cartItems = CartDAO.getCart(user.getEmail());
			}
			
			request.setAttribute("cart", cartItems);
			
			response.setHeader("Cache-control","no-store");
			response.setHeader("Pragma","no-cache");
			response.setDateHeader("Expires", 0);
			
			getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
