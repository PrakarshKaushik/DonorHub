package com.school.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.school.service.AdminService;
import com.school.service.StudentService;
import com.school.service.TeacherService;
import com.school.service.UserService;
import com.school.to.UserTo;

@WebServlet(name = "user", urlPatterns = { "/user" })
public class UserController extends HttpServlet {

	UserService userService = new UserService();

	StudentService studentService = new StudentService();
	AdminService adminService = new AdminService();
	TeacherService teacherService = new TeacherService();

	// doGet method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		switch (action) {
		case "logout": // logout

			HttpSession loggedUser = request.getSession(false);
			loggedUser.invalidate();

			RequestDispatcher loggedOutUserDispatcher = request.getRequestDispatcher("index.jsp");
			loggedOutUserDispatcher.forward(request, response);

			break;

		case "userUpdate": // updating user data
			String userEmail = request.getParameter("email");

			UserTo userToBeUpdatedUser = userService.getOneUserByEmail(userEmail);

			request.setAttribute("userToUpdate", userToBeUpdatedUser);

			RequestDispatcher userToBeUpdateDispatcher = request.getRequestDispatcher("updateStudent.jsp");
			userToBeUpdateDispatcher.forward(request, response);
			break;

		case "userDelete": // deleting user info
			String deleteEmail = request.getParameter("email");

			userService.delete(deleteEmail);

			RequestDispatcher userDeletedDispatcher = request.getRequestDispatcher("welcome.jsp");
			userDeletedDispatcher.forward(request, response);
			break;

		case "teacherUpdate": // updating teacher info
			int teacherId = Integer.parseInt(request.getParameter("id"));

			UserTo teacherUser = teacherService.getTeacherById(teacherId);

			if (teacherUser != null) {
				request.setAttribute("userToUpdate", teacherUser);

				RequestDispatcher teacherUpdateDispatcher = request.getRequestDispatcher("updateTeacher.jsp");
				teacherUpdateDispatcher.forward(request, response);
			}
			break;

		default:
			break;
		}

	}

	// doPost method
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		switch (action) {
		case "register": // register a user
			String registrationEmail = request.getParameter("email");
			String registrationPassword = request.getParameter("password");
			String completeName = request.getParameter("cname");
			String role = request.getParameter("role");
			String UserClass = request.getParameter("class");

			UserTo registerUser = new UserTo();
			registerUser.setUserEmail(registrationEmail);
			registerUser.setPassword(registrationPassword);
			registerUser.setCompleteName(completeName);
			registerUser.setRole(role);
			registerUser.setUserClass(UserClass);

			if (userService.checkUser(registrationEmail)) { // checking if user email already exists and showing alert
				request.setAttribute("actionMessage", "Email already exists !");
				RequestDispatcher registerDispatcher = request.getRequestDispatcher("signup.jsp");
				registerDispatcher.forward(request, response);

			}

			else {
				int registerSuccessStatus = userService.register(registerUser);

				if (registerSuccessStatus != 0) { // register user and displaying success message

					request.setAttribute("actionMessage", "User registered successfully");
					RequestDispatcher registerDispatcher = request.getRequestDispatcher("index.jsp");
					registerDispatcher.forward(request, response);

				}

			}

			break;

		case "login": // sign in
			String loginEmail = request.getParameter("email");
			String loginPassword = request.getParameter("password");

			UserTo loginUser = new UserTo(0, null, loginEmail, loginPassword, null, null, null);
			UserTo loggededInUser = userService.login(loginUser);

			if (loggededInUser == null) {
				request.setAttribute("actionMessage", "Inavlid credentials !!");
				RequestDispatcher invalidCredentialDispatcher = request.getRequestDispatcher("index.jsp");
				invalidCredentialDispatcher.forward(request, response);
			}

			else if (loggededInUser.getAccountStatus().equals("blocked")) {
				request.setAttribute("actionMessage", "Your account has been blocked !!");
				RequestDispatcher blockedUserDispatcher = request.getRequestDispatcher("index.jsp");
				blockedUserDispatcher.forward(request, response);
			}

			else if (loggededInUser.getAccountStatus().equals("unblocked")) {
				HttpSession httpSession = request.getSession();

				httpSession.setAttribute("authenticatedUser", loggededInUser);

				if (loggededInUser.getRole().equals("admin")) {

					RequestDispatcher adminLoggedInDispatcher = request.getRequestDispatcher("welcome.jsp");
					adminLoggedInDispatcher.include(request, response);
				}

				else if (loggededInUser.getRole().equals("student")) {

					RequestDispatcher adminLoggedInDispatcher = request.getRequestDispatcher("welcome.jsp");
					adminLoggedInDispatcher.include(request, response);
				}

				else if (loggededInUser.getRole().equals("teacher")) {

					RequestDispatcher adminLoggedInDispatcher = request.getRequestDispatcher("welcome.jsp");
					adminLoggedInDispatcher.include(request, response);
				}
			}
			break;

		case "adminRegister": // register student and teacher

			String studentEmail = request.getParameter("email");
			String studentPassword = request.getParameter("password");
			String studentName = request.getParameter("cname");
			String studentRole = request.getParameter("role");
			String studentClass = request.getParameter("class");

			if (studentRole.equals("teacher")) {
				studentClass = null;
			}

			UserTo registerStudent = new UserTo();
			registerStudent.setUserEmail(studentEmail);
			registerStudent.setPassword(studentPassword);
			registerStudent.setCompleteName(studentName);
			registerStudent.setRole(studentRole);
			registerStudent.setUserClass(studentClass);

			if (userService.checkUser(studentEmail) && studentRole.equals("student")) {
				request.setAttribute("actionMessage", "Email already exists !");
				RequestDispatcher registerDispatcher = request.getRequestDispatcher("addStudent.jsp");
				registerDispatcher.forward(request, response);

			}

			else if (userService.checkUser(studentEmail) && studentRole.equals("teacher")) {
				request.setAttribute("actionMessage", "Email already exists !");
				RequestDispatcher registerDispatcher = request.getRequestDispatcher("addTeacher.jsp");
				registerDispatcher.forward(request, response);

			}

			else {
				int registerSuccessStatus = userService.register(registerStudent);

				if (registerSuccessStatus != 0) {

					request.setAttribute("actionMessage", "User registered successfully");
					RequestDispatcher registerDispatcher = request.getRequestDispatcher("welcome.jsp");
					registerDispatcher.forward(request, response);

				}

			}

			break;

		case "updateUserDetails": // updating user details
			String updatedEmail = request.getParameter("email");
			String updatedPassword = request.getParameter("password");
			String updatedName = request.getParameter("cname");
			String updatedRole = request.getParameter("role");
			String updatedClass = request.getParameter("class");
			String accountStatus = request.getParameter("blockedStatus");
			int updateUserId = Integer.parseInt(request.getParameter("userid"));

			UserTo updateUser = new UserTo(updateUserId, updatedName, updatedEmail, updatedPassword, updatedRole,
					accountStatus, updatedClass);

			if (userService.updateUser(updateUser) != 0) {
				// returning to welcome page after update
				RequestDispatcher userUpdatedDispatcher = request.getRequestDispatcher("welcome.jsp");
				userUpdatedDispatcher.forward(request, response);

			}

			break;

		default:
			break;
		}

	}

}

//by PRAKARSH KAUSHIK (2536930)