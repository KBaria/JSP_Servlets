<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<main>
		<!-- Title container -->
		<div>
			<p>Login</p>
		</div>
		
		<!-- Login form container-->
		<div>
			<form action="home" method="post" name="login-form" id="login-form">
				<div name="error" class="error-div" style="display: none;">Invalid credentials!</div>
				<!-- <label for="email">Email</label> -->
				<input type="email" 
					name="email" 
					placeholder="Enter email"
					pattern="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+$" 
					value="${email}"
					required><br>
				
				<!-- <label for="password">Password</label> -->
				<input type="password" 
					name="password" 
					placeholder="Enter password" 
					required><br>
					
				<button type="submit">Login</button>
			</form>
		</div>
		
		<!-- Register & Forgot password -->
		<div>
			<form action="register" method="post">
				<button type="submit">Register</button>
			</form>
			<form action="forgot-password" method="post">
				<button type="submit">Forgot password</button>
			</form>
		</div>
	</main>
	
	<script type="text/javascript" src="script/login.js"></script>
</body>
</html>