package com.globallogic.to;

public class BookTO {

	private String bookId;
	private String bookName;
	private String bookPublisher;
	private String bookAuthor;

	public BookTO(String bookId, String bookName, String bookPublisher, String bookAuthor) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookPublisher = bookPublisher;
		this.bookAuthor = bookAuthor;
	}

	public BookTO(String bookId) {
		this.bookId = bookId;

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

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	@Override
	public String toString() {
		return "Books [Id=" + bookId + ", Book Name=" + bookName + ", Publisher=" + bookPublisher + ", Author=" + bookAuthor
				+ "]";
	}

}
