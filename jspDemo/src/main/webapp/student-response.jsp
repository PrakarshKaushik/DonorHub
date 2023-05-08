<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
The Student is confirmed: ${param.fname} ${param.lname}
<br><br>
<ol>
<%
String [] genders = request.getParameterValues("gender");
for(String x: genders){
	out.println("<li>"+x+"</li>");
}
%></ol>
</body>
</html>