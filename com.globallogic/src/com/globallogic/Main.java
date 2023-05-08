package com.globallogic;

import com.globallogic.repo.UserRepo;
import com.globallogic.service.BookService;
import com.globallogic.service.UserService;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		UserService userService = new UserService();
		BookService bookService = new BookService();

		// Welcome Message
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println("----------------------------------------------");
		System.out.println("      Welcome to GlobalLogic Book Store");
		System.out.println("----------------------------------------------");
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		// Choice for Login, Register, Logout
		System.out.print("\nEnter 1 for register, 2 for login, 0 to return to home screen : ");
		int dashboardChoice = 0;
		do {
			dashboardChoice = sc.nextInt();
			switch (dashboardChoice) {
			case 1:
				userService.register();
				break;
			case 2:
				userService.login();
				break;
			case 0:
				logout();
				System.out.println("Successfully Logged Out !!! \n \n");
			default:
				System.out.println("Enter 1 for register, 2 for login, 0 to return to home screen : ");
				break;

			}
		} while (dashboardChoice != 0 || dashboardChoice != 1 || dashboardChoice != 2);

	}

	// Logout - to main screen
	static void logout() {
		main(null);
	}

}
