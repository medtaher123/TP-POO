package ui;

import models.Book;

public class BookDetailsPage extends BackOnlyPage {
	private Book book;
	
	@Override
	protected String getTitle() {
		return book.getShortDisplay();
	}

	public BookDetailsPage(Book book) {
		super();
		this.book = book;
	}
	
	public void printContent() {
		System.out.println("Title: "+book.getTitle());
		System.out.println("author: "+book.getAuthor());
		System.out.println("country: "+book.getCountry());
		System.out.println("language: "+book.getLanguage());
		System.out.println("description: "+book.getDescription());
		System.out.println("pages: "+book.getPages());
		System.out.println("year:"+book.getYear());
		System.out.println("link: "+book.getLink());
		System.out.println("available Copies: "+book.getNumberOfAvailableCopies());
		
				
	}

}
