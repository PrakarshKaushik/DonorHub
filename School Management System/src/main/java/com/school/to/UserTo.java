package com.school.to;

public class UserTo {

	private int userId;
	private String completeName;
	private String userEmail;
	private String password;
	private String role;
	private String accountStatus = "blocked"; // setting account status blocked as default for teacher and student,
												// admin will approved, then allow login
	private String userClass;

	public UserTo() {

	}

	// parameterized constructor
	public UserTo(int userId, String completeName, String userEmail, String password, String role, String accountStatus,
			String userClass) {
		this.userId = userId;
		this.completeName = completeName;
		this.userEmail = userEmail;
		this.password = password;
		this.role = role;
		this.accountStatus = accountStatus;
		this.userClass = userClass;
	}

	// getter for userClass
	public String getUserClass() {
		return userClass;
	}

	// setter for userClass
	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}

	// getter for userId
	public int getUserId() {
		return userId;
	}

	// setter for userId
	public void setUserId(int userId) {
		this.userId = userId;
	}

	// getter for completeName
	public String getCompleteName() {
		return completeName;
	}

	// setter for completeName
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	// getter for userEmail
	public String getUserEmail() {
		return userEmail;
	}

	// setter for userEmail
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	// getter for password
	public String getPassword() {
		return password;
	}

	// setter for password
	public void setPassword(String password) {
		this.password = password;
	}

	// getter for role
	public String getRole() {
		return role;
	}

	// setter for role
	public void setRole(String role) {
		this.role = role;
	}

	// getter for accountStatus
	public String getAccountStatus() {
		return accountStatus;
	}

	// setter for accountStatus
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

}
//by PRAKARSH KAUSHIK (2536930)