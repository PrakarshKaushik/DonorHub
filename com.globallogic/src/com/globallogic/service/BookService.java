package com.globallogic.service;

import java.time.zone.ZoneRulesException;
import java.util.*;

import com.globallogic.repo.BookRepo;
import com.globallogic.to.BookTO;

public class BookService {
	Scanner sc = new Scanner(System.in);

	BookRepo repository = new BookRepo();

	public void addBook() {

		System.out.println("Enter Book ID : ");
		String bookId = sc.nextLine();
		System.out.println("Enter Book Name : ");
		String bookName = sc.nextLine();
		System.out.println("Enter Book Publisher Name : ");
		String bookPublisher = sc.nextLine();
		System.out.println("Enter Book Author Name : ");
		String bookAuthor = sc.nextLine();

		BookTO book = new BookTO(bookId, bookName, bookPublisher, bookAuthor);
		repository.addBook(book);

	}
	
	//Update Book
	public void updateBook() {
		System.out.println("Enter the Book ID you wish to update: ");
		String bookId = sc.nextLine();
		
		BookTO book = new BookTO(bookId);
		try {
			repository.updateBook(book);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	//Remove Book
	public void removeBook() {
		System.out.println("Enter the Book ID you wish to remove: ");
		String bookId = sc.nextLine();
		
		BookTO book = new BookTO(bookId);
		try {
			repository.removeBook(book);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	//Getting All Books Count
		public void getAllCountBooks() {
			repository.findAllCountBooks(null);
		}

	//Getting All Books
	public void getAllBooks() {
		repository.findAllBooks(null);
	}

	//Getting Books by ID
	public void getIDBooks() {
		System.out.println("Enter Book ID : ");
		String bookId = sc.nextLine();
		
		BookTO book = new BookTO(bookId);

		repository.findIDBooks(book);
	}
	
	//Getting Books by Name
	public void getNameBooks() {
		System.out.println("Enter Book Name : ");
		String bookName = sc.nextLine();
		
		BookTO book = new BookTO(bookName);

		repository.findNameBooks(book);
	}
	
	//Getting Books by Publisher NAme
	public void getPublisherBooks() {
		System.out.println("Enter Book Publisher Name : ");
		String bookPublisher = sc.nextLine();
		
		BookTO book = new BookTO(bookPublisher);

		repository.findPublisherBooks(book);
	}
	
	//Getting Books by Author Name	
	public void getAuthorBooks() {
		System.out.println("Enter Book Author Name : ");
		String bookAuthor = sc.nextLine();
		
		BookTO book = new BookTO(bookAuthor);

		repository.findAuthorBooks(book);
	}
	

//Book Search Combo
public void bookSearchCombo() {
	Scanner sc = new Scanner(System.in);
	int optionChoice = 0;
	System.out.println(
			"\n Enter "
			+ "\n1: for Searching book by ID "
			+ "\n2: for searching book by Name "
			+ "\n3: for searching book by Publisher "
			+ "\n4: for searching bookk by Author"
			+ "\n5: for listing all available books");
	do {
		optionChoice = sc.nextInt();
		switch (optionChoice) {
		case 1:
			
			getIDBooks();
			break;
		case 2:
			getNameBooks();
			break;
		case 3:
			getPublisherBooks();
			break;
		case 4:
			getAuthorBooks();
			break;
		case 5:
			getAllBooks();
			break;
		default:
			System.out.println(
					" Enter "
					+ "\n1: for Searching book by ID"
					+ "\n2: for searching book by name"
					+ "\n3: for searching book by Publisher"
					+ "\n4: for searching bookk by Author"
					+ "\n5: for listing all available books");
		}
	} while (optionChoice != 1 || optionChoice != 2 || optionChoice != 3 || optionChoice != 4 || optionChoice != 5);
}

}
