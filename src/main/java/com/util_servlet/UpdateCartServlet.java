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

@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/update-cart"})
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get request object and convert to List<Map<String, Object>>
		Gson gson = new Gson();
		List<Map<String, Integer>> requestData = gson.fromJson(request.getReader(), new TypeToken<List<Map<String, Integer>>>(){}.getType());
		
		// set up response body map
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("cart-update", "successful");
		
		// get session and user object
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		for(Map<String, Integer> itemData : requestData) {
			try {
				// update cart details in database
				CartDAO.updateCart(new Cart(user.getEmail(), itemData.get("id"), itemData.get("qty")));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				responseBody.replace("cart-update", "unsuccessful");
			}
		}
		
		// convert response body map into JSON
		JsonObject responseJson = gson.toJsonTree(responseBody).getAsJsonObject();
		
		// send response to login.js
		response.setContentType("application/json");
		response.getWriter().write(responseJson.toString());
	}
}
