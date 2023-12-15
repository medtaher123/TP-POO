package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import models.Book;
import models.User;

public class BooksService extends DatabaseService {

	public static Book[] getAllBooks() {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.BOOKS_API_URL), Book[].class);
	}

	public static Book[] getAllBooks(String queryParams) {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.BOOKS_API_URL + "?" + queryParams),Book[].class);
	}
	
	public static Book getBookById(String id) {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.BOOKS_API_URL + "/" + id), Book.class);
	}
	
	public static Book UpdateBook(Book book) {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("PUT", DatabaseService.BOOKS_API_URL + "/" + book.getId(),book), Book.class);
	}
	
	public static Book addBook(Book newBook) {
		return new Gson().fromJson(DatabaseService.sendHttpRequest("POST", DatabaseService.BOOKS_API_URL, newBook), Book.class);
	}
	
	public static boolean DeleteBookById(String id) {
		DatabaseService.sendHttpRequest("DELETE", DatabaseService.USERS_API_URL + "/" + id);
		return true; 
	}
	
	
	
}
