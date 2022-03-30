<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Submit Info For Today</h3>
<form action= "SaveInfo">
	<pre>
	State			<input type="text" name="state" />
	UserID			<input type="text" name="userid" />
	TotalCases		<input type="text" name="total" />
	ActiveCases		<input type="text" name="active" />
	TotalDeaths		<input type="text" name="deaths" />
			<input type="submit" value="Submit" />
	</pre>
	<a href="stadmindashboard.jsp">Dashboard</a>
	
</form>
</body>
</html>