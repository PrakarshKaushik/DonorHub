<!-- signup page -->

<!-- imports -->
<%@page import="com.school.to.UserTo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!--     Beginning of HTML -->
<!DOCTYPE html>
<html>

<!-- head of HTML -->
<head>
<meta charset="ISO-8859-1">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
	crossorigin="anonymous">
<title>Signup</title>
</head>

<!--  HTML Body section -->
<body class="bg-dark">

	<%
	UserTo loggedInUser = (UserTo) session.getAttribute("authenticatedUser");

	if (loggedInUser != null)
		response.sendRedirect(
		"welcome.jsp"); /* if user session is invalidated or user not logged in, redirect to login page */
	%>

	<%
	String message = (String) request.getAttribute("actionMessage");

	if (message != null && message.equals("Email already exists !")) { /* check if email/user already present */
	%>
	<div class="alert alert-danger col" role="alert"
		style="text-align: center">
		<%=message%>
		<!-- alert msg is displayed -->
	</div>
	<%
	}
	%>


	<!-- registeration form -->
	<div class="vh-100 d-flex justify-content-center align-items-center"
		style="margin-top: 4rem">
		<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="col-12 col-md-8 col-lg-6">
					<div class="border border-3 border-primary"></div>
					<div class="card shadow-lg" style="background-color: #D5CEA3;">
						<div class="card-body p-4">
							<form class="mb-3 mt-md-4" action="user?action=register"
								method="post">
								<h2 class="fw-bold mb-2">Register</h2>
								<p class=" mb-5">Please enter your details for registration!</p>
								<div class="mb-3">
									<label for="email" class="form-label ">Email address</label> <input
										type="email" class="form-control" id="email" name="email"
										required>
								</div>
								<div class="mb-3">
									<label for="cname" class="form-label ">Complete Name</label> <input
										type="text" class="form-control" id="cname" name="cname"
										required>
								</div>
								<div class="mb-3">
									<label for="password" class="form-label ">Password</label> <input
										type="password" class="form-control" id="password"
										name="password" required>
								</div>
								<div class="mb-3">
									<label for="class" class="form-label ">Class</label> <input
										type="text" class="form-control" id="class" name="class">
								</div>
								<div class="mb-3">
									<select class="form-select" name="role" required>
										<option value="student">Student</option>
										<option value="student">Teacher</option>
									</select>
								</div>
								<br>
								<div class="d-grid">
									<button class="btn btn-outline-dark" type="submit">Signup</button>
								</div>
							</form>
							<div>
								<p class="mb-0  text-center">
									Already have an account? <a href="index.jsp"
										class="text-primary fw-bold">Sign In</a>
								</p>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- by PRAKARSH KAUSHIK (2536930) -->
</body>
</html>