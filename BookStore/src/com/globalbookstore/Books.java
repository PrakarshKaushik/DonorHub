package com.globalbookstore;

import java.util.Scanner;

public class Books {

	private String bookId;
	private String bookName;
	private String bookAuthor;
	private String bookPublisher;

	public void Book(String bookId, String bookName, String bookAuthor, String bookPublisher) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookPublisher = bookPublisher;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	@Override
	public String toString() {
		return "Books \nID=" + bookId + ", "
				+ "\nName=" + bookName + ", "
				+ "\nAuthor=" + bookAuthor + ", "
				+ "\nPublisher=" + bookPublisher + " ";
	}

	// List All books
	static void listAllBooks(Books[] book) {
		int i = 0;
		for (i = 0; i < book.length; i++) {
			System.out.println(book[i]);
		}
	}

	// Book Search
	// by id
	static void bookById(Books[] book) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Book Id : ");
		String idOfBook = sc.nextLine();
		System.out.println();

		boolean flag = false;
		int i = 0;
		for (i = 0; i < book.length; i++) {
			if (!(book[i].getBookId().equals(idOfBook))) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		if (flag) {
			System.out.println("Book not found");
			bookById(book);
		} else {
			System.out.println(book[i]);
		}
	}

	// by name
	static void bookByName(Books[] book) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Book Name : ");
		String nameOfBook = sc.nextLine();
		System.out.println();

		boolean flag = false;
		int i = 0;
		for (i = 0; i < book.length; i++) {
			if (!(book[i].getBookName().equals(nameOfBook))) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		if (flag) {
			System.out.println("Book not found");
			bookByName(book);
		} else {
			System.out.println(book[i]);
		}
	}

	// by author name
	static void bookByAuth(Books[] book) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Author Name : ");
		String authOfBook = sc.nextLine();
		System.out.println();

		boolean flag = false;
		int i = 0;
		for (i = 0; i < book.length; i++) {
			if (!(book[i].getBookAuthor().equals(authOfBook))) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		if (flag) {
			System.out.println("Author not found");
			bookByAuth(book);
		} else {
			System.out.println(book[i]);
		}
	}

	// by author name
	static void bookByPublisher(Books[] book) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Publisher Name : ");
		String pubOfBook = sc.nextLine();
		System.out.println();

		boolean flag = false;
		int i = 0;
		for (i = 0; i < book.length; i++) {
			if (!(book[i].getBookPublisher().equals(pubOfBook))) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		if (flag) {
			System.out.println("Publisher not found");
			bookByPublisher(book);
		} else {
			System.out.println(book[i]);
		}
	}

	// Book Search Combo
	static void bookSearchCombo(Books[] book) {
		Scanner sc = new Scanner(System.in);
		int optionChoice = 0;
		System.out.println(
				" Enter "
				+ "\n1: for Searching book by ID "
				+ "\n2: for searching book by name "
				+ "\n3: for searching book by Author "
				+ "\n4: for searching bookk by publisher"
				+ "\n5: for listing all available books");
		do {
			optionChoice = sc.nextInt();
			switch (optionChoice) {
			case 1:
				bookById(book);
				break;
			case 2:
				bookByName(book);
				break;
			case 3:
				bookByAuth(book);
				break;
			case 4:
				bookByPublisher(book);
				break;
			case 5:
				listAllBooks(book);
				break;
			default:
				System.out.println(
						" Enter "
						+ "\n1: for Searching book by ID"
						+ "\n2: for searching book by name"
						+ "\n3: for searching book by Author"
						+ "\n4: for searching bookk by publisher"
						+ "\n5: for listing all available books");
			}
		} while (optionChoice != 1 || optionChoice != 2 || optionChoice != 3 || optionChoice != 4 || optionChoice != 5);
	}
}

//Online Book Store Assignment - Submitted on 03rd March 2023
//by Prakarsh Kaushik || 2536930 || GlobalLogic - Hitachi
