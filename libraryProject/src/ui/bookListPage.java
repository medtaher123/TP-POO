package ui;

import java.util.concurrent.TimeUnit;

import helpers.QueryParamsBuilder;
import models.Book;
import services.BooksService;

public class bookListPage extends Page {

	private String searchFilter = null;

	public bookListPage() {
	}

	public bookListPage(String searchFilter) {
		this.searchFilter = searchFilter;
	}

	@Override
	protected String getTitle() {
		return "Book List";
	}

	@Override
	public void printContent() {
		// System.out.println("book list");
		Book[] books;
		if (searchFilter != null) {
			System.out.println("search results for: " + searchFilter + "\n");
			QueryParamsBuilder params = new QueryParamsBuilder();
			params.addQueryParam("title_like", searchFilter);
			books = BooksService.getAllBooks(params.toString());
		} else {
			books = BooksService.getAllBooks();
		}
		if (books.length == 0) {
			System.out.println("---no books---");
		}
		Action[] bookActions = mapActions(books);

		new ActionMenu(bookActions, ActionMenu.PREV_PAGE_ACTION, "choose a book wisely!:").execute();
	}

	private Action[] mapActions(Book[] books) {
		Action[] actions = new Action[books.length];
		for (int i = 0; i < books.length; i++) {
			Book b = books[i];
			actions[i] = new Action() {

				@Override
				public String getDescription() {
					return b.getShortDisplay();
				}

				@Override
				public void execute() {
					// System.out.println("TODO: show book details here!");
					PageManager.callPage(new BookDetailsPage(b));
				}
			};
		}
		return actions;
	}

}
