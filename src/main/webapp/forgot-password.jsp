<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot password</title>
</head>
<body>
	<main>
		<div>
			<!-- Info -->
			<div>
				<p>Follow these simple steps to reset your password</p>
				<ul>
					<li>Provide the email address for your account</li>
					<li>You will receive an OTP that you must provide to set a new password</li>
					<li>Set a new password for your account</li>
				</ul>
			</div>
			
			<!-- loader -->
			<div name="loader" class="loader-div" style="display: none;">Please wait while we send an email to you</div>
			
			<!-- Email validation form -->
			<form action="otp-verification" method="post" name="forgot-password-form" id="forgot-password-form">
				<div name="error" class="error-div" style="display: none;">Email not registered!</div>
				<!-- <label for="email">Email</label> -->
					<input type="email" 
						name="email" 
						placeholder="Enter email"
						pattern="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+$" 
						value="${email}"
						required><br>
						
					<button type="submit">Continue</button>
			</form>
			
			<!-- Login & Register -->
			<div class="other-actions-container">
				<form action="login" method="post">
					<button type="submit" id="login-link-button">Login</button>
				</form>
				<form action="register" method="post">
					<button type="submit" id="register-link-button">Register</button>
				</form>
			</div>
		</div>
	</main>
	
	<script type="text/javascript" src="script/forgot_password.js"></script>
</body>
</html>