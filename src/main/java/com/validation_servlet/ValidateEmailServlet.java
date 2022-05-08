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

import com.database.UserDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

@WebServlet(name = "ValidateEmailServlet", urlPatterns = {"/validate-email"})
public class ValidateEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ValidateEmailServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get JSON request body and convert it into a map
		Gson gson = new Gson();
		Map<String, String> credentials = gson.fromJson(request.getReader(), new TypeToken<Map<String,String>>(){}.getType());
		
		// set up response body map
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("email-validation", "unsuccessful");
		
		System.out.println(credentials);
		
		try {
			// check for existence of email in the database
			boolean validEmail = !UserDAO.checkEmailValidity(credentials.get("email"));
			
			// if email exists update response body
			if(validEmail) {
				responseBody.replace("email-validation", "successful");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// convert response body map into JSON
		JsonObject responseJson = gson.toJsonTree(responseBody).getAsJsonObject();
		
		// send response to forgot_password.js
		response.setContentType("application/json");
		response.getWriter().write(responseJson.toString());
	}
}
