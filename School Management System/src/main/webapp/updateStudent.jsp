<!-- Update student details form -->

<!-- imports -->
<%@page import="com.school.to.UserTo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!--   HTML beginning -->
<!DOCTYPE html>
<html>

<!-- head section -->
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
<jsp:include page="./WEB-INF/navbar.jsp" />
<title>Insert title here</title>
</head>

<!-- body of HTML -->
<body style="background-color: #404258">


	<%
	UserTo updateUser = (UserTo) request.getAttribute("userToUpdate");

	if (updateUser != null) {
	%>
	<!-- update form -->
	<div class="vh-100 d-flex justify-content-center align-items-center"
		style="margin-top: 4rem">
		<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="col-12 col-md-8 col-lg-6">
					<div class="border border-3 border-primary"></div>
					<div class="card shadow-lg" style="background-color: #6B728E">
						<div class="card-body p-4">
							<form class="mb-3 mt-md-4" action="user?action=updateUserDetails"
								method="post">
								<h2 class="fw-bold mb-2 ">Update Your details</h2>
								<p class=" mb-5">Please enter your details to update!</p>
								<div class="mb-3">
									<label for="email" class="form-label ">Email address</label> <input
										type="email" class="form-control" id="email" name="email"
										value="<%=updateUser.getUserEmail()%>">
								</div>
								<div class="mb-3">
									<label for="cname" class="form-label ">Complete Name</label> <input
										type="text" class="form-control" id="cname" name="cname"
										required value="<%=updateUser.getCompleteName()%>">
								</div>
								<div class="mb-3">
									<label for="password" class="form-label ">Password</label> <input
										type="password" class="form-control" id="password"
										name="password" value="<%=updateUser.getPassword()%>" required>
								</div>
								<%
								if (updateUser.getRole().equals("admin")) {
								%>

								<div class="mb-3">
									<select class="form-select" name="role" required>
										<option value="admin" selected="selected">Admin</option>
										<option value="student">Student</option>
										<option value="teacher">Teacher</option>
									</select>
								</div>
								<%
								} else if (updateUser.getRole().equals("teacher")) {
								%>
								<div class="mb-3">
									<select class="form-select" name="role" required>
										<option value="admin">Admin</option>
										<option value="student">Student</option>
										<option value="teacher" selected="selected">Teacher</option>
									</select>
								</div>
								<%
								} else {
								%>
								<div class="mb-3">
									<label for="class" class="form-label ">Class</label> <input
										type="text" class="form-control" id="class" name="class"
										readonly="readonly" required
										value="<%=updateUser.getUserClass()%>">
								</div>
								<div class="mb-3">
									<select class="form-select" name="role" required>
										<option value="admin">Admin</option>
										<option value="student" selected="selected">Student</option>
										<option value="teacher">Teacher</option>
									</select>
								</div>

								<%
								}
								%>
								<br>
								<%
								if (updateUser.getAccountStatus().equals("blocked")) {
								%>

								<div class="mb-3">
									<select class="form-select" name="blockedStatus" required>
										<option value="blocked" selected="selected">Blocked</option>
										<option value="unblocked">Unblocked</option>
									</select>
								</div>
								<%
								} else {
								%>
								<div class="mb-3">
									<select class="form-select" name="blockedStatus" required>
										<option value="blocked">Blocked</option>
										<option value="unblocked" selected="selected">Unblocked</option>
									</select>
								</div>
								<%
								}
								%>
								<input type="text" class="form-control" id="userid"
									name="userid" readonly="readonly"
									value="<%=updateUser.getUserId()%>" hidden>



								<div class="d-grid">
									<button class="btn btn-outline-dark" type="submit">Update
										User</button>
								</div>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%
	}
	%>
	<!-- by PRAKARSH KAUSHIK (2536930) -->
</body>
</html>