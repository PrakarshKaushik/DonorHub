<!-- NAVBAR - from bootstrap -->

<!-- imports -->
<%@page import="com.school.to.UserTo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- HTML doc starting -->
<!DOCTYPE html>
<html>
<!-- head of HTML -->
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
<title>${sessionScope.authenticatedUser.getCompleteName()}'s
	Dashboard</title>
<!-- displaying name of logged in user in title tab -->
</head>

<!-- body of HTML -->
<body>

	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies

	UserTo userCheck = (UserTo) session.getAttribute("authenticatedUser");

	/* first check if user is null, if not then proceeds further */
	if (userCheck != null) {

		/* check if user is admin or not, if admin then display navbar with admin functionality */
		if (userCheck.getRole().equals("admin")) {
	%>
	<nav class="navbar navbar-expand-lg" style="background-color: #18122B;">
		<div class="container-fluid">
			<a class="navbar-brand text-white"><%=userCheck.getCompleteName()%>
				(Admin) <!-- displaying name of logged in user with designation  -->
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">

				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<button class="btn btn-warning btn-sm"
						style="-bs-btn-font-size: .75rem;">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="addStudent.jsp">Add Student</a></li>
						<!-- admin can insert new student -->
					</button>
					<button class="btn btn-warning btn-sm"
						style="-bs-btn-font-size: .75rem; margin-left: 0.2rem;">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="addTeacher.jsp">Add Teacher</a></li>
						<!-- admin can insert new teacher -->
					</button>
					<li class="nav-item"><a class="nav-link active text-white"
						aria-current="page" href="welcome.jsp"><i
							class="bi bi-arrow-left-short"></i>Back</a></li>
					<!-- while on update page, teacher can go back to dashboard if he changes his mind to update info -->

				</ul>
			</div>
			<div class="d-flex flex-row bd-highlight">
				<div class="p-2 bd-highlight">
					<a class="nav-link active" aria-current="page"
						href="user?action=logout" style="color: white">Logout <i
						class="bi bi-power" style="color: red"></i></a>
					<!-- logout button > returns to login page -->
				</div>
			</div>
		</div>
	</nav>


	<%
	}

	/* checking if user is teacher and displaying navbar accordingly */
	else if (userCheck.getRole().equals("teacher")) {
	%>

	<nav class="navbar navbar-expand-lg" style="background-color: #18122B;">
		<div class="container-fluid">
			<a class="navbar-brand text-white">Welcome, <%=userCheck.getCompleteName()%>
				(Teacher) <!-- displaying name of logged in user with designation  -->
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active text-white"
						aria-current="page"
						href="user?action=teacherUpdate&id=<%=userCheck.getUserId()%>">Update
							profile</a></li>
					<!-- Teacher can update his data -->
					<li class="nav-item"><a class="nav-link active text-white"
						aria-current="page" href="welcome.jsp"><i
							class="bi bi-arrow-left-short"></i>Back</a></li>
					<!-- while on update page, teacher can go back to dashboard if he changes his mind to update info -->
				</ul>
			</div>
			<div class="d-flex flex-row bd-highlight">
				<div class="p-2 bd-highlight">
					<a class="nav-link active" aria-current="page"
						href="user?action=logout" style="color: white">Logout <i
						class="bi bi-power" style="color: red"></i></a>
					<!-- logout button > returns to login page -->
				</div>
			</div>
		</div>
		</div>
	</nav>


	<%
	}

	/* check if user is student and displaying navbar for same */
	else if (userCheck.getRole().equals("student")) {
	%>

	<nav class="navbar navbar-expand-lg" style="background-color: #18122B;">
		<div class="container-fluid">
			<a class="navbar-brand text-white">Welcome, <%=userCheck.getCompleteName()%>
				(Student) <!-- displaying name of logged in user with designation  -->
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse " id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item "><a class="nav-link active text-white"
						aria-current="page" href="welcome.jsp"><i
							class="bi bi-arrow-left-short "></i>Back</a></li>
					<!-- if on update page and not changing data, press to go back
				</ul> -->
			</div>
			<div class="d-flex flex-row bd-highlight">
				<div class="p-2 bd-highlight">
					<a class="nav-link active" aria-current="page"
						href="user?action=logout" style="color: white">Logout <i
						class="bi bi-power" style="color: red"></i></a>
					<!-- logout button > returns to login page -->
				</div>
			</div>
		</div>
		</div>
	</nav>


	<%
	}

	}

	/* if session is invalided, go to homepage */
	else {
	response.sendRedirect("index.jsp");
	}
	%>

	<!-- by PRAKARSH KAUSHIK (2536930) -->


</body>
</html>