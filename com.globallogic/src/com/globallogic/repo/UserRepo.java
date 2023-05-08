package com.globallogic.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.globallogic.db.DBConnection;
import com.globallogic.service.BookService;
import com.globallogic.service.UserService;
import com.globallogic.to.BookTO;
import com.globallogic.to.UserTO;

public class UserRepo {

	Connection connection = DBConnection.getConnection();
	BookRepo bookRepository = new BookRepo();
	BookService bookService = new BookService();
	
	public void register(UserTO user) {

		try {

			PreparedStatement statement = connection.prepareStatement("insert into user values(?, ?, ?, ?)");

			statement.setString(1, user.getUserId());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getRole());

			statement.executeUpdate();
			System.out.println("User Registered \n");
		} catch (Exception e) {
			System.out.println("inside regsiter of UserRepository");
		}

	}

	public void login(String userId, String password) {
		try {

			PreparedStatement statement = connection
					.prepareStatement("select * from user where userId = ? AND password = ?");

			statement.setString(1, userId);
			statement.setString(2, password);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next() == true) {
				if (resultset.getString(4).toLowerCase().equals("admin")) {
					System.out.println("In Admin Dashboard");
					System.out.println("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
					System.out.println("          Welcome to Admin Dashboard");
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					adminDashboard();
					
				}

				else if (resultset.getString(4).toLowerCase().equals("user")) {
					System.out.println("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
					System.out.println("          Welcome to User Dashboard");
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					userDashboard();
				}

			} else {
				System.out.println("Invalid");
			}

		} catch (Exception e) {

			System.out.println("inside catch of Login of UserRepo");
		}

	}
	
	//User Dashboard Options
		public void userDashboard() {
			Scanner sc = new Scanner(System.in);
			int optionChoice = 0;
			System.out.println(
					"\n Enter "
					+ "\n1: for Searching book (by ID, by Name, by Publisher, by Author) "
					+ "\n2: for Adding Books to Favorites "
					+ "\n3: for Adding Books to Completed "
					+ "\n9: for going to previous menu"
					+ "\n0: to log out");
			do {
				optionChoice = sc.nextInt();
				switch (optionChoice) {
				case 1:
					bookService.bookSearchCombo();
					break;
				case 2:
					System.out.println("Adding to Favourites Under Construction");
					break;
				case 3:
					System.out.println("Adding to Completed Under Construction");
					break;
				case 9:
					System.out.println("Going to previous mwnu");
					break;
				case 0:
					System.out.println("Logging our");
					break;
				default:
					System.out.println(
							" Enter "
									+ "\n1: for Searching book (by ID, by Name, by Publisher, by Author) "
									+ "\n2: for Adding Books to Favorites "
									+ "\n3: for Adding Books to Completed "
									+ "\n9: for going to previous menu"
									+ "\n0: to log out");
					break;
				}
			} while (optionChoice != 1 || optionChoice != 2 || optionChoice != 3 || optionChoice != 9 || optionChoice != 0);
		}

		
		//Admin Dashboard Options
				public void adminDashboard() {
					Scanner sc = new Scanner(System.in);
					int optionChoice = 0;
					System.out.println(
							"\n Enter "
							+ "\n1: for Searching book (by ID, by Name, by Publisher, by Author) "
							+ "\n2: for Inserting new Book "
							+ "\n3: for Updating a Book"
							+ "\n4: for Removing a Book"
							+ "\n5: for Counting Total Books"
							+ "\n6: for Sorting  Books  "
							+ "\n9: for going to previous menu"
							+ "\n0: to log out");
					do {
						optionChoice = sc.nextInt();
						switch (optionChoice) {
						case 1:
							bookService.bookSearchCombo();
							break;
						case 2:
							bookService.addBook();
							break;
						case 3:
							bookService.updateBook();
							break;
						case 4:
							bookService.removeBook();
							break;
						case 5:
							bookService.getAllCountBooks();
							break;
						case 6:
							System.out.println("Sorting books functionality under construction");
							break;
						case 9:
							System.out.println("Going to previous mwnu");
							break;
						case 0:
							System.out.println("Logging out");
							break;
						default:
							System.out.println(
									"\n Enter "
											+ "\n1: for Searching book (by ID, by Name, by Publisher, by Author) "
											+ "\n2: for Inserting new Book "
											+ "\n3: for Updating a Book"
											+ "\n4: for Removing a Book"
											+ "\n5: for Counting Total Books"
											+ "\n6: for Sorting  Books  "
											+ "\n9: for going to previous menu"
											+ "\n0: to log out");
							break;
						}
					} while (optionChoice != 1 || optionChoice != 2 || optionChoice != 3 || optionChoice != 4 || optionChoice != 5 || optionChoice != 6 || optionChoice != 9 || optionChoice != 0);
				}

}
