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

/**
 * Servlet implementation class ValidateResetPasswordServlet
 */
@WebServlet(name = "ValidateResetPasswordServlet", urlPatterns = {"/validate-password-reset"})
public class ValidateResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ValidateResetPasswordServlet() {
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
		Map<String, String> credentials = gson.fromJson(request.getReader(), new TypeToken<Map<String, String>>(){}.getType());
		
		// set up response body map
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("password-reset", "unsuccessful");
		
		// get session and the recipient email
		HttpSession session = request.getSession();
		String recipientEmail = (String) session.getAttribute("recipientEmail");
		
		try {
			// get corresponding user
			User user = UserDAO.getUser(recipientEmail);
			
			// check if old password is not equal to new password and update response body
			// update the users password in database and remove recipient email attribute from session
			if(!credentials.get("newPassword").equals(user.getPassword())) {
				responseBody.replace("password-reset", "successful");
				user.setPassword(credentials.get("newPassword"));
				UserDAO.updateUser(user);
				
				session.removeAttribute("recipientEmail");
			}
		} catch (SQLException e) {
			// if any error occurs update response body
			responseBody.put("error", "error");
			e.printStackTrace();
		}
		
		// convert response body map into JSON
		JsonObject responseJson = gson.toJsonTree(responseBody).getAsJsonObject();
		
		// send response to login.js
		response.setContentType("application/json");
		response.getWriter().write(responseJson.toString());
	}

}
