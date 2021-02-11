<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LoginPage</title>
</head>
<body>
<h3>${ipAdrs}</h3>
<form action="./loginProcess" method="post" modelAttribute="user">
Email: 		<input type="text" name="email" placeholder="Enter Register email" required><br>
Password: 	<input type="text" name="password" placeholder="Enter password" required>
			<input type="submit" value="Login">
</form>
${errorFromUserDaoImpl}
${logout}
<br>don't have account?<a href= "./register">  Register here</a>
</body>
</html>