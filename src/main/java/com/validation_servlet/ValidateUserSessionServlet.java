package com.validation_servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.User;

@WebServlet(name = "ValidateUserSessionServlet", urlPatterns = {"/validate-user"})
public class ValidateUserSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ValidateUserSessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get session and user object
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		// set up response body map
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("user-session", "invalid");
		
		// check for user
		if(user != null) {
			responseBody.put("user-session", "valid");
		}
		
		// convert response body map into JSON
		Gson gson = new Gson();
		JsonObject responseJson = gson.toJsonTree(responseBody).getAsJsonObject();
		
		// send response to login.js
		response.setContentType("application/json");
		response.getWriter().write(responseJson.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
