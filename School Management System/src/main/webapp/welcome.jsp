<!-- welcome page after log in  -->

<!-- imports -->
<%@page import="com.school.service.AdminService"%>
<%@page import="com.school.service.StudentService"%>
<%@page import="java.util.List"%>
<%@page import="com.school.service.UserService"%>
<%@page import="com.school.to.UserTo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- beginning of HTML  -->
<!DOCTYPE html>
<html>

<!-- head section of HTML -->
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<jsp:include page="./WEB-INF/navbar.jsp" />
<!-- importing navbar -->
<title></title>
</head>

<!-- body section of HTML -->
<body style="background-color: #191919">
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies

	UserTo loggedUser = (UserTo) session.getAttribute("authenticatedUser");
	String userCount = (String) request.getAttribute("countAllUsers");

	if (loggedUser == null) {
		response.sendRedirect("index.jsp");
	} /* if user is not logged in or session terminated , redirects to login */

	else {
		if (loggedUser.getRole().equals("admin")) { /* if user if admin display data of both student and teacher */
			AdminService adminService = new AdminService();
			List<UserTo> studentsAndTeachers = adminService.getStudentsAndTeachers();
	%>
	<div class="mt-5" style="background-color: white">
		<table class="table table-striped table-hover table-dark">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Name</th>
					<th scope="col">Email</th>
					<th scope="col">Role</th>
					<th scope="col">AccountStatus</th>
					<th scope="col">Update</th>
					<th scope="col">Delete</th>
				</tr>
			</thead>
			<tbody class="table-group-divider">
				<%
				/* display all details of user in admin dashboard */
				for (int i = 0; i < studentsAndTeachers.size(); i++) {
				%>
				<tr>
					<th scope="row"><%=i + 1%></th>
					<td><%=studentsAndTeachers.get(i).getCompleteName()%></td>
					<td><%=studentsAndTeachers.get(i).getUserEmail()%></td>
					<td><%=studentsAndTeachers.get(i).getRole()%></td>
					<td><%=studentsAndTeachers.get(i).getAccountStatus()%></td>
					<td><a
						href="user?action=userUpdate&email=<%=studentsAndTeachers.get(i).getUserEmail()%>"><button
								class="btn btn-sm btn-primary">
								<i class="bi bi-pencil-fill"></i>
							</button></a></td>
					<td><a
						href="user?action=userDelete&email=<%=studentsAndTeachers.get(i).getUserEmail()%>"><button
								class="btn btn-sm btn-danger">
								<i class="bi bi-trash-fill"></i>
							</button></a></td>
				</tr>

				<%
				}
				%>

			</tbody>
		</table>
	</div>

	<%
	}

	else if (loggedUser.getRole().equals("teacher")) { /* display details of student in teacher dashboard */

	StudentService studentService = new StudentService();
	List<UserTo> students = studentService.getAllStudents();
	%>
	<div class="mt-5" style="background-color: white">
		<table class="table table-striped table-hover table-dark">
			<thead">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Name</th>
					<th scope="col">Email</th>
					<th scope="col">Role</th>
					<th scope="col">AccountStatus</th>
					<th scope="col">Update</th>
					<th scope="col">Delete</th>
				</tr>
			</thead>
			<tbody class="table-group-divider">

				<%
				for (int i = 0; i < students.size(); i++) {
				%>

				<tr>
					<th scope="row"><%=i + 1%></th>
					<td><%=students.get(i).getCompleteName()%></td>
					<td><%=students.get(i).getUserEmail()%></td>
					<td><%=students.get(i).getRole()%></td>
					<td><%=students.get(i).getAccountStatus()%></td>
					<td><a
						href="user?action=userUpdate&email=<%=students.get(i).getUserEmail()%>"><button
								class="btn btn-sm btn-primary">
								<i class="bi bi-pencil-square"></i>
							</button></a></td>
					<td><a
						href="user?action=userDelete&email=<%=students.get(i).getUserEmail()%>"><button
								class="btn btn-sm btn-danger">
								<i class="bi bi-trash3-fill"></i>
							</button></a></td>
				</tr>


				<%
				}
				%>
			</tbody>
		</table>
	</div>

	<%
	}

	else {
	%>

	<div class="mt-5" style="background-color: white"">
		<table class="table table-striped table-hover table-dark">
			<thead">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Name</th>
					<th scope="col">Email</th>
					<th scope="col">Class</th>
					<th scope="col">Role</th>
					<th scope="col">AccountStatus</th>
					<th scope="col">Update</th>
				</tr>
			</thead>
			<tbody class="table-group-divider">

				<tr>
					<th scope="row"><%=1%></th>
					<td><%=loggedUser.getCompleteName()%></td>
					<td><%=loggedUser.getUserEmail()%></td>
					<td><%=loggedUser.getUserClass()%></td>
					<td><%=loggedUser.getRole()%></td>
					<td><%=loggedUser.getAccountStatus()%></td>
					<td><a
						href="user?action=userUpdate&email=<%=loggedUser.getUserEmail()%>"><button
								class="btn btn-sm btn-primary">
								<i class="bi bi-pencil-square"></i>
							</button></a></td>

				</tr>
			</tbody>
		</table>
	</div>
	<%
	}
	}
	%>
	<!-- by PRAKARSH KAUSHIK (2536930) -->
</body>
</html>