<%
	String userid = (String) session.getAttribute("userid");
	if(userid==null){
		response.sendRedirect("index.jsp");
	}

%>

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
	TotalCases		<input type="text" name="total" />
	ActiveCases		<input type="text" name="active" />
	TotalDeaths		<input type="text" name="deaths" />
			<input type="submit" value="Submit" />
	</pre>
	<a href="stadmindashboard.jsp">Dashboard</a>
	
</form>
</body>
</html>