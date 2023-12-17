package Managers;

import helpers.QueryParamsBuilder;
import models.BookCopy;
import services.BookingsService;

public class BookCopyManager {

	public static boolean isAvailable(BookCopy book) {
		QueryParamsBuilder params = new QueryParamsBuilder();
		params.addQueryParam("bookId", book.getId());
		return BookingsService.getBooksActiveBooking(book.getId()) == null;
	}

}
