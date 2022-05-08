<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buy Product</title>
<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/buy-now.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Rubik&display=swap" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<jsp:include page="/common pages/navbar.jsp"></jsp:include>
	
	<main>
		
		<!-- Title -->
		<div class="info-container">
			<p>Total price : <span class="cart-total-value product-price"></span>
		</div>
		
		<!-- List container -->
		<div class="product-list-container">
			<ul class="product-list">
				<c:forEach items="${cart}" var="cartItem">
					<li class="cart-list-item">
						<div class="product-data-container">
							<div class="product-img-container">
								<img alt="product-image" src="https://via.placeholder.com/150">
							</div>
							<div class="cart-item-data" data-productId = "${cartItem.getProductId()}">
								<p class="product-name">${cartItem.getProduct().getName()}</p>
								<!-- <p class="product-description">${cartItem.getProduct().getDescription()}</p> -->
								<p class="product-category">${cartItem.getProduct().getCategory().getName()}</p>
								<p class="product-unit-price" data-price="${cartItem.getProduct().getPrice()}"></p>
							</div>
						</div>
						
						<div class="product-qty-container">
							<p>Quantity</p>
							<button type="button" class="increment-button">+</button>
							<p class="product-qty">${cartItem.getQty()}</p>
							<button type="button" class="decrement-button">-</button>
						</div>
						
						<div class="cart-subtotal-container">
							<p>Sub total : <span class="item-subtotal" data-price="${cartItem.getQty() * cartItem.getProduct().getPrice()}"></span></p>
						</div>
						
						<!-- 
						<div>
							<form action="buy-now?productId=${product.getId()}" method="post">
								<button class="buy-now-button" type="submit">Buy now</button>
							</form>
							<form action="">
								<button class="add-to-cart-button" type="button">Remove</button>
							</form>
						</div>
						 -->
					</li>
				</c:forEach>
			</ul>
		</div>
		
		<div class="form-container">
			<p>Set up your shipping address</p>
			<form action="${formAction}" method="post" name="checkout-form" id="checkout-form">
				<div class="input-field">
					<label for="address">Address</label>
       				<input type="text" name="address" value="${user.getAddress().getAddress()}" required>
				</div>
       			
       			<div class="input-field">
       				<label for="state">State</label>
       				<input type="text" name="state" value="${user.getAddress().getState()}" required>
       			</div>
       			
       			<div class="input-field">
       				<label for="city">City</label>
       				<input type="text" name="city" value="${user.getAddress().getCity()}" required>
       			</div>
       			
       			<div class="input-field">
       				<label for="pin">Pin</label>
       				<input type="text" name="pin" value="${user.getAddress().getPin()}" required>
       			</div>

				<button class="checkout-button" type="submit">Checkout</button>
			</form>
		</div>
	</main>
	
	<script type="text/javascript" src="script/buy_now.js"></script>
</body>
</html>