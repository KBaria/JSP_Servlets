<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Summary</title>
</head>
<body>
	<main>
		<!-- Navigation -->
		<jsp:include page="/common pages/navbar.jsp"></jsp:include>
		
		<!-- Title -->
		<div>
			
		</div>
		
		<!-- Info -->
		<div>
			<p>${orderDateTime}</p>
			<p>Total price : <span class="cart-total-value"></span>
		</div>
		
		<!-- List container -->
		<div>
			<ul class="product-list">
				<c:forEach items="${orders}" var="orderItem">
					<li class="cart-list-item">
						<div>
							<div>
								<img alt="product-image" src="https://via.placeholder.com/150">
							</div>
							<div class="order-item-data" data-productId = "${orderItem.getProductId()}">
								<p class="product-name">${orderItem.getProduct().getName()}</p>
								<!-- <p class="product-description">${cartItem.getProduct().getDescription()}</p> -->
								<p class="product-category">${orderItem.getProduct().getCategory().getName()}</p>
								<p class="product-unit-price" data-price="${orderItem.getProduct().getPrice()}"></p>
							</div>
						</div>
						
						<div>
							<p class="product-qty">${orderItem.getQty()}</p>
						</div>
						
						<div class="order-subtotal-container">
							<p class="item-subtotal" data-price="${orderItem.getQty() * orderItem.getProduct().getPrice()}"></p>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</main>
	
	<script type="text/javascript" src="script/order_summary.js"></script>
</body>
</html>