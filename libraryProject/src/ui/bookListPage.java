package ui;

import java.util.concurrent.TimeUnit;

import models.Book;
import services.BooksService;

public class bookListPage extends Page {

	@Override
	public void display() {
		System.out.println("book list");
		Book[] books = BooksService.getAllBooks();
		
		Action[] bookActions = mapActions(books);
		
		new ActionMenu(bookActions,ActionMenu.PREV_PAGE_ACTION,"choose a book wisely!:").execute();
	}

	private Action[] mapActions(Book[] books) {
		Action[] actions = new Action[books.length];
		for (int i=0;i<books.length;i++) {
			Book b = books[i]; 
			actions[i] = new Action(){

				@Override
				public String getDescription() {
					return b.getShortDisplay();
				}

				@Override
				public void execute() {
					//System.out.println("TODO: show book details here!");
					PageManager.callPage(new BookDetailsPage(b));
				}};
		}
		return actions;
	}
}
