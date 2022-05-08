<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<main>
		<!-- Title container -->
		<div>
			<p>Register</p>
		</div>
		
		<!-- Login form container-->
		<div>
			<form action="home" method="post" name="registration-form" id="registration-form">
				<input type="text"
					name="first-name"
					placeholder="First name"
					value="${firstName}"
					required><br>

				<input type="text"
					name="last-name"
					placeholder="Last name"
					value="${lastName}"
					required><br>

				<div name="email-error" class="error-div" style="display: none;">Email already registered!</div> 
				<input type="email" 
					name="email" 
					placeholder="Enter email"
					pattern="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+$" 
					value="${email}"
					required><br>
				
				<div name="password-error" class="error-div" style="display: none;">Passwords do not match!</div>
				<input type="password" 
					name="password" 
					placeholder="Enter password" 
					required><br>

				<input type="password" 
					name="cnf-password" 
					placeholder="Confirm password" 
					required><br>
				
				<div name="contact-error" class="error-div" style="display: none;">Contact already registered!</div>
				<input type="tel"
					name="contact"
					placeholder="Contact number without extension"
					pattern="[6789][0-9]{9}"
					required><br>
					
				<button type="submit" id="submit-form">Register</button>
			</form>
		</div>
		
		<!-- Login -->
		<div>
			<form action="login" method="post">
				<button type="submit">Login</button>
			</form>
		</div>
	</main>
	<script type="text/javascript" src="script/register.js"></script>
</body>
</html>