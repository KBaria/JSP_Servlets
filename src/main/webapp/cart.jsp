<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/cart.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Rubik&display=swap" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<jsp:include page="/common pages/navbar.jsp"></jsp:include>
	
	<main>
		
		
		<!-- Title -->
		<div>
			
		</div>
		
		<!-- Info -->
		<div class="empty-cart" style="display: none;">
			<p>Cart is empty :(</p>
		</div>
		<div class="non-empty-cart">
			<div>
				<button class="buy-cart-button" type="button" onclick="updateCart()">Update Cart</button>
			</div>
			<form action="buy-cart-products" method="post">
				<button class="update-cart-button" type="submit">Buy cart products</button>
			</form>
			<p>Total price : <span class="cart-total-value"></span>
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
								<p class="product-unit-price" data-price="${cartItem.getProduct().getPrice()}">${cartItem.getProduct().getPrice()}</p>
							</div>
						</div>
						
						<div class="product-qty-container">
							<button type="button" class="increment-button">+</button>
							<p class="product-qty">${cartItem.getQty()}</p>
							<button type="button" class="decrement-button">-</button>
						</div>
						
						<div class="cart-subtotal-container">
							<p>Sub total</p>
							<p class="cart-subtotal">${cartItem.getQty() * cartItem.getProduct().getPrice()}</p>
						</div>
						<div>
							<!-- 
							<form action="buy-now?productId=${product.getId()}" method="post">
								<button class="buy-now-button" type="submit">Buy now</button>
							</form> 
							-->
							<form action="">
								<button class="remove-item-button" type="button">Remove</button>
							</form>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</main>
	
	<script type="text/javascript" src="script/cart.js"></script>
</body>
</html>