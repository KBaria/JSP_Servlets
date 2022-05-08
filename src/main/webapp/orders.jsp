<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders</title>
<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/orders.css">
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
		
		<!-- List container -->
		<div class="order-list-container">
			<ul class="order-list">
				<c:forEach items="${orderList}" var="order">
					<div class="order-info-container">
						<p>Order date : ${order.getOrderDate()}</p>
						<p>Order Total : <span class="order-total">${order.getTotal()}</span></p>
					</div>
					<ul class="product-list">
						<c:forEach items="${order.getOrders()}" var="orderItem">
							<li class="cart-list-item">
								<div class="product-data-container">
									<div class="product-img-container">
										<img alt="product-image" src="https://via.placeholder.com/150">
									</div>
									<div class="order-item-data" data-productId = "${orderItem.getProductId()}">
										<p class="product-name">${orderItem.getProduct().getName()}</p>
										<!-- <p class="product-description">${cartItem.getProduct().getDescription()}</p> -->
										<p>Category : <span class="product-category">${orderItem.getProduct().getCategory().getName()}</span></p>
										<p>Unit price : <span class="product-unit-price" data-price="${orderItem.getProduct().getPrice()}"></span></p>
									</div>
								</div>
								
								<div class="product-qty-container">
									<p class="product-qty">Quantity : ${orderItem.getQty()}</p>
								</div>
								
								<div class="order-subtotal-container">
									<p>Sub total : <span class="item-subtotal" data-price="${orderItem.getQty() * orderItem.getProduct().getPrice()}"></span></p>
								</div>
							</li>
						</c:forEach>
					</ul>
				</c:forEach>
			</ul>
		</div>
	</main>
	
	<script type="text/javascript" src="script/orders.js"></script>
</body>
</html>