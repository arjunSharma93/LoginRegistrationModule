<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>${ipAdrs}</h3>
<form action="./registrationProcess" method="post" modelAttribute="user">
Name: 		<input type="text" name="name" placeholder="Enter Name" required><br>
Email: 		<input type="text" name="email" placeholder="Enter Email" required><br>
Mobile: 	<input type="text" name="mobile" placeholder="Enter Mobile" required><br>
Password: 	<input type="password" name="password" placeholder="Enter Password" required><br>
<input type="hidden" name="role" value="user">
			<input type="submit" value="Registration">
</form>
<h3>${errorFromUserDaoImpl}</h3>
<br>already have account?<a href= "./login">  Login</a>
</body>
</html>