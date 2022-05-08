package com.validation_servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.UserDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.model.User;

@WebServlet(name = "ValidateLoginServlet", urlPatterns = {"/validate-login"})
public class ValidateLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ValidateLoginServlet() {
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
		Map<String, String> credentials = gson.fromJson(request.getReader(), new TypeToken<HashMap<String, Object>>(){}.getType());
		
		// set up response body map
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("user-login", "unsuccessful");
		
		try {
			// get user object after querying in database
			User user = UserDAO.getUser(credentials.get("email"), credentials.get("password"));
			
			// if user found update login status in response body
			if(user != null) {
				// get session and add the user object into the session
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				responseBody.replace("user-login", "successful");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// convert response body map into JSON
		JsonObject responseJson = gson.toJsonTree(responseBody).getAsJsonObject();
		
		// send response to login.js
		response.setContentType("application/json");
		response.getWriter().write(responseJson.toString());
	}
}
