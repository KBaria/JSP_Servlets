package com.util_servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import com.database.UserDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.model.User;
import com.utils.EmailUtils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

@WebServlet(name = "GenerateOTPServlet", urlPatterns = {"/generate-otp"})
public class GenerateOTPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
    private String port;
    private String email;
    private String name;
    private String pass;
    
    public void init() {
    	// reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        email = context.getInitParameter("email");
        name = context.getInitParameter("name");
        pass = context.getInitParameter("pass");
    }

    public GenerateOTPServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get JSON request body and convert it into a map
		Gson gson = new Gson();
		Map<String, String> credentials = gson.fromJson(request.getReader(), new TypeToken<HashMap<String, Object>>(){}.getType());
		
		// set up response body map
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("email-dispatch", "unsuccessful");
		
		try {
			// get corresponding user
			User user = UserDAO.getUser(credentials.get("email"));
			
			// generate a random alpha numeric password
			String rndPass = RandomStringUtils.randomAlphanumeric(10);
			
			// set email body
			String message = "Hello " + user.getFirstName() + ",\n"
					+ "\nHere is your OTP : " + rndPass
					+ "\nUse this to reset your password\n"
					+ "\nHave a nice day\n"
					+ "\nRegards,"
					+ "\nStardew Mini Mart";
			
			// set subject
			String subject = "Password Reset";
			
			// send message
			EmailUtils.sendEmail(host, port, email, name, pass, credentials.get("email"), subject, message);
			
			// store random password in session
			HttpSession session = request.getSession();
			session.setAttribute("rndPass", rndPass);
			session.setAttribute("recipientEmail", credentials.get("email"));
			
			// on successful generation of OTP and email dispatch update response body
			responseBody.replace("email-dispatch", "successful");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
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
