<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Forgot Password</h2>

	<form action="/22110368/forgot" method="post">
		<label for="email">Enter your email:</label> <input type="email"
			id="email" name="email" required> <label for="email">Enter
			your username:</label> <input type="text" id="username" name="username" required>
		<label for="email">Enter your new password:</label> <input type="password"
			id="password" name="password" required> <input type="submit"
			value="Submit">
	</form>

	<p style="color: red;">
		<%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%>
	</p>
</body>
</html>