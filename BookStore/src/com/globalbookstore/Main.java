package com.globalbookstore;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Admin Database
		Admin[] admin = new Admin[1];
		admin[0] = new Admin();

		// Admin 1
		admin[0].setAdminId("LostBoy");
		admin[0].setAdminPass("java!4me");

		// User Database
		Users[] user = new Users[3];
		user[0] = new Users();
		user[1] = new Users();
		user[2] = new Users();

		// User 1
		user[0].setUserId("12");
		user[0].setUserName("Anurag");
		user[0].setUserLoginId("Anurag43");
		user[0].setUserLoginPass("spokes1");

		// User 2
		user[1].setUserId("23");
		user[1].setUserName("Ashutosh");
		user[1].setUserLoginId("Ashuto541");
		user[1].setUserLoginPass("Table10is");

		// User 3
		user[2].setUserId("09");
		user[2].setUserName("Jaydev");
		user[2].setUserLoginId("itsraj");
		user[2].setUserLoginPass("notdev");

		// Book Database

		Books[] book = new Books[7];
		book[0] = new Books();
		book[1] = new Books();
		book[2] = new Books();
		book[3] = new Books();
		book[4] = new Books();


		// Book1
		book[0].setBookId("65");
		book[0].setBookName("Theory of Everything");
		book[0].setBookAuthor("Stephen Hawking");
		book[0].setBookPublisher("Jaico Publishing");

		// Book2
		book[1].setBookId("43");
		book[1].setBookName("Concept of Physics");
		book[1].setBookAuthor("H C Verma");
		book[1].setBookPublisher("Bharti Bhawan");

		// Book3
		book[2].setBookId("72");
		book[2].setBookName("Rich Dad Poor Dad");
		book[2].setBookAuthor("Robert Kiyaosaki");
		book[2].setBookPublisher("Plata Publishing");

		// Book4
		book[3].setBookId("121");
		book[3].setBookName("The Subtle Art");
		book[3].setBookAuthor("Mark Manson");
		book[3].setBookPublisher("Harper Collins");

		// Book5
		book[4].setBookId("132");
		book[4].setBookName("Atomic Habits");
		book[4].setBookAuthor("James Clear");
		book[4].setBookPublisher("Random House");
		
//		// Book5
//		book[5].setBookId("Null");
//		book[5].setBookName("Null");
//		book[5].setBookAuthor("Null");
//		book[5].setBookPublisher("Null");
//		
//		// Book6
//		book[6].setBookId("Null");
//		book[6].setBookName("Null");
//		book[6].setBookAuthor("Null");
//		book[6].setBookPublisher("Null");
				
		
		//Beginning of Code and User Screen

		// Welcome Message
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println("----------------------------------------------");
		System.out.println("        Welcome to Global Book Store");
		System.out.println("----------------------------------------------");
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		// Option for User or Admin
		System.out.println("\n \nPress 0 for Admin Login || Press 1 for User Login");
		int optionAdminUser = 0;
		do {
			optionAdminUser = sc.nextInt();
			switch (optionAdminUser) {
			case 0:
				System.out.println("\nWelcome to Admin Login");
				Admin.adminLoginPrompt(admin, book);
				break;

			case 1:
				System.out.println("\nWelcome to User Login");
				Users.UserLoginPrompt(user, book);
				break;
			default:
				System.out.println("Enter 0 for Admin Login or 1 for User Login");
			}
		} while (optionAdminUser != 0 || optionAdminUser != 1);
	}
	
	//To Be done
//Add go back one window option
//Add logout option in every tab
//Add Favorites Option
	
	
//Logout function - takes to home screen	
	static void Logout() {
		main(null);
	}

}

//Online Book Store Assignment - Submitted on 03rd March 2023
// by Prakarsh Kaushik || 2536930 || GlobalLogic - Hitachi
