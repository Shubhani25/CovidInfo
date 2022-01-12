<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>State Account</title>
</head>
<body>
<h3>State Account Details</h3>
<hr>
<form action="StateAccountCreationServlet" method="post">
<table border="0">
<tr>
	<td>UserID</td>
	<td><input type="text" name="uid"/></td>
</tr>
<tr>
	<td>Password</td>
	<td><input type="password" name="password"/></td>
</tr>
<tr>
	<td>State</td>
	<td><select name="state">
	<option>Andhra Pradesh	</option>
	<option>Arunachal Pradesh	</option>
	<option>Assam	</option>
	<option>Bihar	</option>
	<option>Chhattisgarh	</option>
	<option>Goa	</option>
	<option>Gujarat	</option>
	<option>Haryana	</option>
	<option>Himachal Pradesh</option>
	<option>Jharkhand	</option>
	<option>Karnataka	</option>
	<option>Kerala	</option>
	<option>Madhya Pradesh	</option>
	<option>Maharashtra	</option>
	<option>Manipur	</option>
	<option>Meghalaya	</option>
	<option>Mizoram	</option>
	<option>Nagaland	</option>
	<option>Odisha	</option>
	<option>Punjab	</option>
	<option>Rajasthan	</option>
	<option>Sikkim	</option>
	<option>Tamil Nadu	</option>
	<option>Telangana	</option>
	<option>Tripura	</option>
	<option>Uttar Pradesh	</option>
	<option>Uttarakhand	</option>
	<option>West Bengal</option>
	</select></td>
</tr>
<tr>
	<td><input type="submit" value="Create Account"/></td>
	<td><input type="reset" name="Clear All"/></td>
</tr>
</table>
</form>
<a href="index.jsp">Home</a>
</body>
</html>