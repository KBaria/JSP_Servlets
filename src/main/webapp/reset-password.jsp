<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset password</title>
</head>
<body>
	<main>
		<div>
			<!-- Info -->
			<div>
				
			</div>
			
			<!-- Reset password form -->
			<form action="" method="post" name="reset-password-form" id="reset-password-form">
				<div name="error" class="error-div" style="display: none;">Passwords do not match!</div>
				<div name="old-pass-error" class="error-div" style="display: none;">Old and new passwords cannot be equal!</div>
				<label for="new-password">Password</label>
				<input type="password" 
					name="new-password" 
					placeholder="Enter password" 
					required><br>
					
				<label for="password">Password</label>
				<input type="password" 
					name="cnf-password" 
					placeholder="Enter password" 
					required><br>
					
				<button type="submit">Reset password</button>
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
	
	<script type="text/javascript" src="script/reset_password.js"></script>
</body>
</html>