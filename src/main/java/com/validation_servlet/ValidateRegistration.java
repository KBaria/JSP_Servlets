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
import com.model.User;

@WebServlet(name = "ValidateRegistrationServlet", urlPatterns = {"/validate-registration"})
public class ValidateRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ValidateRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get JSON request body and convert it into a user object
		Gson gson = new Gson();
		User user = gson.fromJson(request.getReader(), User.class);
		
		// set up a response body map
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("user-registration", "unsucessful");
		
		try {
			// check for email and contact duplication
			boolean emailValidity = UserDAO.checkEmailValidity(user.getEmail());
			boolean contactValidity = UserDAO.checkContactValidity(user.getContact());
			
			// if email & contact are valid insert user into database
			if(emailValidity && contactValidity) {
				UserDAO.insertUser(user);
				
				// get session and add the user object into the session
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				// update registration status in the response body
				responseBody.replace("user-registration", "successful");
			}else {
				// set the display property
				String emailError = emailValidity?"none":"block";
				String contactError = contactValidity?"none":"block";
				
				responseBody.put("email-validity", emailError);
				responseBody.put("contact-validity", contactError);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		// convert response body map into JSON
		JsonObject responseJson = gson.toJsonTree(responseBody).getAsJsonObject();
		
		// send response to register.js
		response.setContentType("application/json");
		response.getWriter().write(responseJson.toString());
	}
}
