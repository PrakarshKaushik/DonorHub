<%@page import="com.school.to.UserTo"%>
<%@page import="javax.servlet.http.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
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
<title>Login</title>
</head>
<body class="bg-dark">
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies

	UserTo loggedInUser = (UserTo) session.getAttribute("authenticatedUser");

	if (loggedInUser != null) {

		response.sendRedirect("welcome.jsp");
	}
	%>



	<div class="vh-100 d-flex justify-content-center align-items-center">
		<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="col-12 col-md-8 col-lg-6">
					<div class="border border-3 border-primary"></div>
					<div class="card shadow-lg" style="background-color: #D5CEA3;">

						<div class="card-body p-5">

							<%
							String message = (String) request.getAttribute("actionMessage");

							if (message != null && message.equals("User registered successfully")) {
							%>

							<div class="alert alert-success col-8" role="alert">
								<%=message%>
							</div>

							<%
							}

							else if (message != null && message.equals("Inavlid credentials !!")) {
							%>
							<div class="alert alert-danger col-8" role="alert">
								<%=message%>
							</div>

							<%
							}

							else if (message != null && message.equals("Your account has been blocked !!")) {
							%>
							<div class="alert alert-danger col-8" role="alert">
								<%=message%>
							</div>
							<%
							}
							%>

							<form class="mb-3 mt-md-4" action="user?action=login"
								method="post">
								<h2 class="fw-bold mb-2 text-uppercase ">Login</h2>
								<p class=" mb-5">Enter your login id and password!</p>
								<div class="mb-3">
									<label for="email" class="form-label ">Email address</label> <input
										type="email" class="form-control" id="email" name="email"
										required>
								</div>
								<div class="mb-3">
									<label for="password" class="form-label ">Password</label> <input
										type="password" class="form-control" id="password"
										name="password" required>
								</div>
								<br>
								<div class="d-grid">
									<button class="btn btn-outline-dark" type="submit">Login</button>
								</div>
							</form>
							<div>
								<p class="mb-0  text-center">
									Don't have an account? <a href="signup.jsp"
										class="text-primary fw-bold">Register Now</a>
								</p>
							</div>

						</div>
					</div>
					<div class="border border-3 border-primary"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- by PRAKARSH KAUSHIK (2536930) -->
</body>
</html>