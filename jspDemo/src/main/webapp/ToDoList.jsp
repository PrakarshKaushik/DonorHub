<%@page import="org.apache.jasper.tagplugins.jstl.core.Param"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="ToDoList.jsp">
  <label for="task">To Do: </label>
  <input type="text" id="task" name="task"><br>
  <input type="submit" value="Add">
</form> 

<% List<String> toDo = (List<String>) session.getAttribute("toDoList");

if(toDo == null){
	toDo = new ArrayList<String>();
	session.setAttribute("toDoList", toDo);
}

String task = request.getParameter("task");
if(task != null){
	toDo.add(task);
}
%>

<hr>
<h3>To Do :</h3>
<ol>
<%for(String x : toDo){
	out.println("<li>"+x+"</li>");
	}%></ol>

<%  %>
</body>
</html>