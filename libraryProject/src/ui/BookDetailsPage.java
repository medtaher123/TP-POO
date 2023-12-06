package ui;

import models.Book;

public class BookDetailsPage extends Page {
	private Book book;

	public BookDetailsPage(Book book) {
		super();
		this.book = book;
	}
	@Override
	public void display() {
		ConsoleHelper.printCenteredString(book.getShortDisplay(), '*');
		System.out.println("Title");
	}

}
