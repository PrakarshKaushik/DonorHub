package com.globallogic.repo;

import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.globallogic.db.DBConnection;
import com.globallogic.exception.BookDeletionFailed;
import com.globallogic.exception.UpdateFailed;
import com.globallogic.service.BookService;

import com.globallogic.to.BookTO;

public class BookRepo {
	Connection connection = DBConnection.getConnection();

	// Add a book to database
	public void addBook(BookTO book) {

		try {

			PreparedStatement statement = connection.prepareStatement("insert into book values(?, ?, ?, ?)");

			statement.setString(1, book.getBookId());
			statement.setString(2, book.getBookName());
			statement.setString(3, book.getBookPublisher());
			statement.setString(4, book.getBookAuthor());

			statement.executeUpdate();
			System.out.println("Book Added \n");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("inside regsiter of BookRepository");
		}

	}
	
	// Update a book in database
		public void updateBook(BookTO book) throws Exception {

			try {

				PreparedStatement statement = connection.prepareStatement("update book set bookId = ?, bookName = ?, bookPublisher = ?, bookAuthor= ?");

				statement.setString(1, book.getBookId());
				statement.setString(2, book.getBookName());
				statement.setString(3, book.getBookPublisher());
				statement.setString(4, book.getBookAuthor());

				statement.executeUpdate();
				System.out.println("Book Added \n");
			} catch (Exception e) {
				throw new UpdateFailed();
			}

		}
		
		// Remove a book From database
				public void removeBook(BookTO book) throws Exception {

					try {

						PreparedStatement statement = connection.prepareStatement("DELETE from book WHERE id = ?");

						statement.setString(1, book.getBookId());
				         
				         
				         int rowsDeleted = statement.executeUpdate();
				         if (rowsDeleted > 0) {
				            System.out.println("Book removed successfully.");
				         } else {
				            System.out.println("No book found with the given ID.");
				         }
						statement.executeUpdate();
						System.out.println("Book Removed \n");
					} catch (Exception e) {
						throw new BookDeletionFailed();
					}

				}
				
				// List Total Count books
				public void findAllCountBooks(BookTO book) {

					List<BookTO> books = new ArrayList<BookTO>();
					try {

						PreparedStatement statement = connection.prepareStatement("select count(id) from book");

						ResultSet resultset = statement.executeQuery();

						while (resultset.next()) {

							String bookId = resultset.getString(1);
							String bookName = resultset.getString(2);
							String bookPublisher = resultset.getString(3);
							String bookAuthor = resultset.getString(4);

							BookTO bookTO = new BookTO(bookId, bookName, bookPublisher, bookAuthor);
							books.add(bookTO);
						}
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("inside catch of BooKRepo");
					}

					System.out.println(books);
				}

	// List All books
	public void findAllBooks(BookTO book) {

		List<BookTO> books = new ArrayList<BookTO>();
		try {

			PreparedStatement statement = connection.prepareStatement("select * from book");

			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {

				String bookId = resultset.getString(1);
				String bookName = resultset.getString(2);
				String bookPublisher = resultset.getString(3);
				String bookAuthor = resultset.getString(4);

				BookTO bookTO = new BookTO(bookId, bookName, bookPublisher, bookAuthor);
				books.add(bookTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("inside catch of BooKRepo");
		}

		System.out.println(books);
	}

// Search Book By ID
public  void findIDBooks(BookTO book) {
		List<BookTO> books = new ArrayList<BookTO>();
		try {

			PreparedStatement statement = connection.prepareStatement("select * from book where bookId = ?");

			statement.setString(1, book.getBookId());
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {

				String bookId = resultset.getString(1);
				String bookName = resultset.getString(2);
				String bookPublisher = resultset.getString(3);
				String bookAuthor = resultset.getString(4);

				BookTO bookTO = new BookTO(bookId, bookName, bookPublisher, bookAuthor);
				books.add(bookTO);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("inside catch of BooKRepo");
		}
		
		System.out.println(books);
}

//Search Book By Book Name
public  void findNameBooks(BookTO book) {
		List<BookTO> books = new ArrayList<BookTO>();
		try {

			PreparedStatement statement = connection.prepareStatement("select * from book where bookName = ?");

			statement.setString(2, book.getBookName());
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {

				String bookId = resultset.getString(1);
				String bookName = resultset.getString(2);
				String bookPublisher = resultset.getString(3);
				String bookAuthor = resultset.getString(4);

				BookTO bookTO = new BookTO(bookId, bookName, bookPublisher, bookAuthor);
				books.add(bookTO);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("inside catch of BooKRepo");
		}
		
		System.out.println(books);
}

//Search Book By Book Publisher Name
public  void findPublisherBooks(BookTO book) {
		List<BookTO> books = new ArrayList<BookTO>();
		try {

			PreparedStatement statement = connection.prepareStatement("select * from book where bookPublisher = ?");

			statement.setString(3, book.getBookPublisher());
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {

				String bookId = resultset.getString(1);
				String bookName = resultset.getString(2);
				String bookPublisher = resultset.getString(3);
				String bookAuthor = resultset.getString(4);

				BookTO bookTO = new BookTO(bookId, bookName, bookPublisher, bookAuthor);
				books.add(bookTO);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("inside catch of BooKRepo");
		}
		
		System.out.println(books);
}

//Search Book By Author
public  void findAuthorBooks(BookTO book) {
		List<BookTO> books = new ArrayList<BookTO>();
		try {

			PreparedStatement statement = connection.prepareStatement("select * from book where bookAuthor = ?");

			statement.setString(4, book.getBookId());
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {

				String bookId = resultset.getString(1);
				String bookName = resultset.getString(2);
				String bookPublisher = resultset.getString(3);
				String bookAuthor = resultset.getString(4);

				BookTO bookTO = new BookTO(bookId, bookName, bookPublisher, bookAuthor);
				books.add(bookTO);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("inside catch of BooKRepo");
		}
		
		System.out.println(books);
}

}
