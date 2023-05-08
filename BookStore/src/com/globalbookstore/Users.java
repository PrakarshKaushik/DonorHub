package com.globalbookstore;

import java.util.Scanner;

public class Users {
	private String userId;
	private String userName;
	private String userLoginId;
	private String userLoginPass;

	public void User(String userId, String userName, String userLoginId, String userLoginPass) {
		this.userId = userId;
		this.userName = userName;
		this.userLoginId = userLoginId;
		this.userLoginPass = userLoginPass;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getUserLoginPass() {
		return userLoginPass;
	}

	public void setUserLoginPass(String userLoginPass) {
		this.userLoginPass = userLoginPass;
	}

	// Login Function
	// User Login
	static void UserLoginPrompt(Users[] user, Books[] book) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter User ID : ");
		String userLogin = sc.nextLine();
		System.out.print("Enter User Pass : ");
		String userPass = sc.nextLine();
		System.out.println();

		boolean flag = false;

		for (int i = 0; i < user.length; i++) {
			if (!(user[i].getUserLoginId().equals(userLogin) && user[i].getUserLoginPass().equals(userPass))) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		if (flag) {
			System.out.println("Invalid User Id or Password");
			UserLoginPrompt(user, book);
		} else {
			System.out.println("valid");
			userDash(book);
		}

	}

	// User Dashboard
	static void userDash(Books[] book) {
		Scanner sc = new Scanner(System.in);
		int userDashOption = 0;
		System.out.println("Enter "
				+ "\n1: to Search Books"
				+ "\n2: to Add Books to Favourite");

		do {
			userDashOption = sc.nextInt();
			switch (userDashOption) {
			case 1:
				Books.bookSearchCombo(book);
				break;
			case 2:
				System.out.println("Adding to Favourites under development....Try again after 5 business days");
				break;
			default:
				System.out.println("Enter "
						+ "\n1: to Search Books"
						+ "\n2: to Add Books to Favourite");
			}
		} while (userDashOption != 1 || userDashOption != 2);
	}

}

//Online Book Store Assignment - Submitted on 03rd March 2023
//by Prakarsh Kaushik || 2536930 || GlobalLogic - Hitachi
