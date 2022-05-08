<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Navigation</title>
<link rel="stylesheet" href="css/navbar.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Rubik&display=swap" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<div class="navbar-container">
		<!-- Logo -->
		<a id="home-link" href="home">
			<p>Mini Mart</p>
		</a>
		<ul class="nav-links">
			<c:if test="${user == null}">
				<li><a id="login-link" href="login">Login</a></li>
				<li><a id="register-link" href="register">Register</a></li>
			</c:if>
			<c:if test="${user != null}">
				<li><a href="logout">Logout</a></li>
			</c:if>
			<li><a id="cart-link" href="cart">Cart</a></li>
			<li><a href="orders">Orders</a></li>
		</ul>
	</div>
</body>
</html>