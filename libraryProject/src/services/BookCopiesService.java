package services;

import com.google.gson.Gson;

import helpers.QueryParamsBuilder;
import models.BookCopy;

public class BookCopiesService extends DatabaseService {

	public static BookCopy[] getAllBookCopies() {
		Gson gson = new Gson();
		return gson.fromJson(sendHttpRequest("GET", BOOK_COPIES_API_URL), BookCopy[].class);
	}

	public static BookCopy[] getAllBookCopies(String queryParams) {
		Gson gson = new Gson();
		return gson.fromJson(sendHttpRequest("GET", BOOK_COPIES_API_URL + "?" + queryParams),BookCopy[].class);
	}
	
	public static BookCopy getBookCopyById(String id) {
		Gson gson = new Gson();
		return gson.fromJson(sendHttpRequest("GET", BOOK_COPIES_API_URL + "/" + id), BookCopy.class);
	}
	
	public static BookCopy[] getCopiesOfBook(String bookId) {
		QueryParamsBuilder params = new QueryParamsBuilder();
		params.addQueryParam("bookId", bookId);
		return getAllBookCopies(params.toString());
	}
	public static BookCopy[] getAvailableCopiesOfBook(String bookId) {
		QueryParamsBuilder params = new QueryParamsBuilder();
		params.addQueryParam("bookId", bookId);
		params.addQueryParam("isAvailable", "true");
		return getAllBookCopies(params.toString());
	} 
	
	public static BookCopy UpdateBookCopy(BookCopy book) {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("PUT", DatabaseService.BOOK_COPIES_API_URL + "/" + book.getId(),book), BookCopy.class);
	}
	
	public static BookCopy addBookCopy(BookCopy newBookCopy) {
		return new Gson().fromJson(DatabaseService.sendHttpRequest("POST", DatabaseService.BOOK_COPIES_API_URL, newBookCopy), BookCopy.class);
	}
	
	public static boolean DeleteBookCopyById(String id) {
		DatabaseService.sendHttpRequest("DELETE", DatabaseService.BOOK_COPIES_API_URL + "/" + id);
		return true; //TODO: return status instead
	}

	
	
	
}
