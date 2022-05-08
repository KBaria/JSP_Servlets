<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Validate OTP</title>
</head>
<body>
	<main>
		<div>
			<!-- Info -->
			<div>
				
			</div>
			
			<!-- loader -->
			<div name="loader" class="loader-div" style="display: none;">Please wait</div>
		
			<!-- OTP validation form -->
			<form action="reset-password" method="post" name="otp-validation-form" id="otp-validation-form">
				<div name="error" class="error-div" style="display: none;">Incorrect OTP!</div>
				<!-- <label for="password">Password</label> -->
				<input type="password" 
					name="password" 
					placeholder="Enter password" 
					required><br>
					
				<button type="submit">Validate OTP</button>
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
	
	<script type="text/javascript" src="script/validate_otp.js"></script>
</body>
</html>