package com.util_servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.CartDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.model.Cart;
import com.model.User;

@WebServlet(name = "AddToCartServlet", urlPatterns = {"/add-to-cart"})
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get JSON request body and convert it into a map
		Gson gson = new Gson();
		Map<String, String> requestData = gson.fromJson(request.getReader(), new TypeToken<HashMap<String, Object>>(){}.getType());
		
		// set up response body map
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("cart-update", "unsuccessful");
		
		// get session and the user object
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		// if user is not present in session
		if(user != null) {
			try {
				// get corresponding cart items
				List<Cart> cartItems = CartDAO.getCart(user.getEmail());
				
				// get product id from request data
				int productId = Integer.parseInt(requestData.get("id"));
				
				// if no cart items are present add to database
				if(cartItems == null) {
					CartDAO.addToCart(new Cart(user.getEmail(), productId, 1));
					responseBody.put("cart-item", "added-new");
				}else {
					// check if product with same id is present
					Cart existingProduct = cartItems.stream()
							.filter(item -> item.getProductId() == productId)
							.findFirst()
							.orElseGet(() -> null);
					
					// if not add new cart item
					if(existingProduct == null) {
						CartDAO.addToCart(new Cart(user.getEmail(), productId, 1));
						responseBody.put("cart-item", "added-new");
					}else {
						// if present increment quantity
						CartDAO.updateCart(new Cart(user.getEmail(), productId, existingProduct.getQty() + 1));
						responseBody.put("cart-item", "incremented");
					}
				}
				
				responseBody.replace("cart-update", "successful");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			responseBody.put("user-session", "invalid");
		}
		
		// convert response body map into JSON
		JsonObject responseJson = gson.toJsonTree(responseBody).getAsJsonObject();
		
		// send response to login.js
		response.setContentType("application/json");
		response.getWriter().write(responseJson.toString());
	}
}
