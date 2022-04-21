 <%
	String userid = (String) session.getAttribute("userid");
	if(userid==null){
		response.sendRedirect("index.jsp");
	}

%>
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>State Admin</title>
</head>
<body>
	<h5>Welcome State Admin!</h5>
	<a href="entrypage.jsp">Add Info of Today</a>
	<br>
	<a href="">Update Info</a>
	<br>
	<a href="StateDataServlet">View Info for Own State</a>
	<br>
	<a href="AllDataServlet">View Info for All States</a>
	<br>
	<a href="EndSession">Logout</a>
    <br>
</body>
</html>