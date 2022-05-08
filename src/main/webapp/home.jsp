<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="css/home.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Rubik&display=swap" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<jsp:include page="/common pages/navbar.jsp"></jsp:include>
	
	<main>
		<div class="product-list-container">
			<ul class="product-list">
				<c:forEach items="${products}" var="product">
					<li class="product-list-item">
						<div class="product-data-container">
							<div class="product-img-container">
								<img alt="product-image" src="https://via.placeholder.com/150">
							</div>
							<div class="product-data" data-productId = "${product.getId()}">
								<p class="product-name">${product.getName()}</p>
								<p class="product-description">${product.getDescription()}</p>
								<p>Category : <span class="product-category">${product.getCategory().getName()}</span></p>
								<p>price : <span class="product-price" data-price="${product.getPrice()}"></span></p>
							</div>
						</div>
						<div class="product-action-container">
							<form action="buy-now?productId=${product.getId()}" method="post">
								<button class="buy-now-button" type="submit">Buy now</button>
							</form>
							<form action="">
								<button class="add-to-cart-button" type="button">Add to cart</button>
							</form>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</main>
	
	<script type="text/javascript" src="script/home.js"></script>
</body>
</html>