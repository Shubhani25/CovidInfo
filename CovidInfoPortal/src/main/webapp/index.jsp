<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Covid Info</title>
</head>
<body>
	<h3>Covid Information Portal</h3>
	<hr>
	<form action = "VerifyUser" method="post">
		<table border="0">
			<tr>
				<td>Email:</td>
				<td><input type="text" name = "uid"/></td>
			</tr>	
			<tr>
				<td>Password:</td>
				<td><input type="password" name = "password"/></td>
			</tr>
			<tr>
				<td>User Type:</td>
				<td><select name = "utype">
						<option>EndUser</option>
						<option>State-Admin</option>
						<option>Super-Admin</option>
					</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"/></td>
				<td><input type="reset" value = "Reset"/></td>
			</tr>
		</table>
	</form>
	<hr>
	<a href = "Registration.jsp">New User Registration</a>
	
</body>
</html>