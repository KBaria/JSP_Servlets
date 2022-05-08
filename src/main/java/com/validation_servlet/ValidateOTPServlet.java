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
import com.google.gson.reflect.TypeToken;

@WebServlet(name = "ValidateOTPServlet", urlPatterns = {"/validate-otp"})
public class ValidateOTPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ValidateOTPServlet() {
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
		responseBody.put("otp-validation", "unsuccessful");
		
		// get session and the generated OTP
		HttpSession session = request.getSession();
		String rndPass = (String) session.getAttribute("rndPass");
		
		// if user provided OTP and generated OTP are equal update response
		// and remove generated OTP from session
		if(credentials.get("otp").equals(rndPass)) {
			responseBody.replace("otp-validation", "successful");
			session.removeAttribute("rndPass");
		}
		
		// convert response body map into JSON
		JsonObject responseJson = gson.toJsonTree(responseBody).getAsJsonObject();
		
		// send response to login.js
		response.setContentType("application/json");
		response.getWriter().write(responseJson.toString());
	}

}
