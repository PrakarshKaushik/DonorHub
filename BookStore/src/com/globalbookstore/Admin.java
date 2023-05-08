package com.globalbookstore;

import java.util.Scanner;

public class Admin {

	private String adminId;
	private String adminPass;

	public void Admins(String adminId, String adminPass) {
		this.adminId = adminId;
		this.adminPass = adminPass;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

	// Login Function
	// Admin Login
	static void adminLoginPrompt(Admin[] admin, Books[] book) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Admin ID : ");
		String adminLoginId = sc.nextLine();
		System.out.print("Enter Admin Pass : ");
		String adminLoginPass = sc.nextLine();
		System.out.println();

		boolean flag = false;
		for (int i = 0; i < admin.length; i++) {
			if (!(admin[i].getAdminId().equals(adminLoginId) && admin[i].getAdminPass().equals(adminLoginPass))) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		if (flag) {
			System.out.println("Invalid Admin ID or Pass");
			adminLoginPrompt(admin, book);
		} else {
			adminDash(book);
		}
	}

	// Insert Book by Admin
		static void bookInsert(Books[] book) {
			Scanner sc = new Scanner(System.in);
			for(int i=0; i<book.length; i++) {
				
				
				if(book[i] == null) {
					book[i]=new Books();
					System.out.print("Enter the new Book ID : ");
					String BookID = sc.nextLine();
					System.out.print("\nEnter the new Book Name : ");
					String BookName = sc.nextLine();
					System.out.print("\nEnter the new Book Author : ");
					String BookAuthor = sc.nextLine();
					System.out.print("\nEnter the new Book Publisher : ");
					String BookPublisher = sc.nextLine();
					book[i].setBookId(BookID);
					book[i].setBookName(BookName);
					book[i].setBookAuthor(BookAuthor);
					book[i].setBookPublisher(BookPublisher);
					System.out.println("Book inserted successfully");
					adminDash(book);
					
				}
				else {
					continue;
				}
			}
		}
		
	// Remove Book by Admin
		static void bookRemove(Books[] book) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter the Book Id you want to Remove : ");
			String BookIDToRemove = sc.nextLine();
			for(int i=0; i<book.length; i++) {
				if(book[i] != null) {
				if(book[i].getBookId().equals(BookIDToRemove)) {
					System.out.println("Book not present in Database");
			}
				else {
					book[i] = null;
					adminDash(book);
				}}
		}
		}
	// Update Book by Admin
			static void bookUpdate(Books[] book) {
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter the Book Id you want to Update : ");
				String BookIDToUpdate = sc.nextLine();
				for(int i=0; i<book.length;i++) {
					if(book[i] == null) {
						break;
					}
					else if(book[i].getBookId().equals(BookIDToUpdate)) {
						System.out.print("Enter the Book ID to update : ");
						String BookID = sc.nextLine();
						System.out.print("\nEnter the  Book Name to update : ");
						String BookName = sc.nextLine();
						System.out.print("\nEnter the Book Author to update : ");
						String BookAuthor = sc.nextLine();
						System.out.print("\nEnter the new Book Publisher to update : ");
						String BookPublisher = sc.nextLine();
						book[i].setBookId(BookID);
						book[i].setBookName(BookName);
						book[i].setBookAuthor(BookAuthor);
						book[i].setBookPublisher(BookPublisher);
						System.out.println("Book successfully updated");
						adminDash(book);
					}
					else {
						System.out.println("Book not present in Database");
						break;
					}
				}
			}

	// Admin Dashboard - after login
	static void adminDash(Books[] book) {

		Scanner sc = new Scanner(System.in);
		int adminDashChoice = 0;
		System.out.println(
				"Enter "
				+ "\n1: to Search Books"
				+ "\n2: to insert a new book"
				+ "\n3: to remove an existing book"
				+ "\n4: to update details of a book");
		do {
			adminDashChoice = sc.nextInt();
			switch (adminDashChoice) {
			case 1:
				Books.bookSearchCombo(book);
				break;
			case 2:
				bookInsert(book);
				break;
			case 3:
				bookRemove(book);
				break;
			case 4:
				bookUpdate(book);
				break;
			default:
				System.out.println(
						"Enter "
						+ "\n1: to Search Books"
						+ "\n2: to insert a new book"
						+ "\n3: to remove an existing book"
						+ "\n4: to update details of a book");
			}
		} while (adminDashChoice != 1 || adminDashChoice != 2 || adminDashChoice != 3 || adminDashChoice != 4);
	}

}


//Online Book Store Assignment - Submitted on 03rd March 2023
//by Prakarsh Kaushik || 2536930 || GlobalLogic - Hitachi