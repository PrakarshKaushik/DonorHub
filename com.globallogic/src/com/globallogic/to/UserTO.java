package com.globallogic.to;

public class UserTO {

	private String userId;
	private String password;
	private String email;
	private String role;

	public UserTO(String userId, String password, String email, String role) {

		this.userId = userId;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

/*
 * class Multi3 implements Runnable{ public void run(){
 * System.out.println("thread is running..."); }
 * 
 * public static void main(String args[]){ Multi3 m1=new Multi3(); Thread t1
 * =new Thread(m1); // Using the constructor Thread(Runnable r) t1.start(); } }
 */
