<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<main>
		<!-- List container -->
		<div>
			<ul>
				<c:forEach items="" var="">
					<img alt="product-image" src="#">
					<p>Product name</p>
					<p>Product Description</p>
					<p>Product category</p>
					<p>Product price</p>
					
					<form action="">
						<input type="number" min="1" required>
						<button type="submit">Update Quantity</button>
					</form>
					
					<p>Sub Total</p>
					<p>00</p>
				</c:forEach>
			</ul>
		</div>
		
		<!-- Checkout -->
		<div>
			<form action="">
				<label for="address">Address</label>
				<input type="text"
					name="address"
					value=""
					required>
					
				<label for="state">State</label>
				<input type="text"
					name="state"
					value=""
					required>
					
				<label for="city">City</label>
				<input type="text"
					name="city"
					value=""
					required>
				
				<label for="pin">PIN code</label>
				<input type="text"
					name="pin"
					value=""
					required>
				
				<button type="submit">Checkout</button>
			</form>
		</div>
	</main>
</body>
</html>