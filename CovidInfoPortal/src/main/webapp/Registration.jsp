<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
</head>
<body>
<h3>Registration Form</h3>
<hr>
<form action = "RegistrationServlet" method = "post">
	<pre>
		Email		<input type = "text" name = "uid"/>
		Password	<input type = "password" name  = "password" />
		Name		<input type="text" name="name"/>
		Address		<input type="text" name="address" />
		Mobile		<input type="text" name="mobile" />
		<br>
		<input type="submit" value="Submit">	<input type = "reset" value="Reset"/>
	</pre>
</form>
<hr>
<a href = "index.jsp">HOME</a>
</body>
</html>