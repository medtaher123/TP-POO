package helpers;

import models.BookCopy;
import models.User;
import services.BookCopiesService;
import services.UsersService;

public class BarcodeScannerSimulator {
	//Created this to simulate the scanning of a barcode of a book or user id in a real library
	public static User scanUser() {
		System.out.println("**Simulating a barcode scanner (users have their id on a barcode on their card)**\n**BIIP**\nyou can open the db.json file to get a user id for testing");
		while(true) {
			User user = UsersService.getUserById(ConsoleHelper.input("userId"));
			if(user!=null)
				return user;
			System.out.println("user not found");
		}
	}
	
	public static BookCopy scanBook() {
		System.out.println("**Simulating a barcode scanner (books have their id on a barcode on the first page)**\n**BIIP**\nyou can open the db.json file to get a bookCopy id for testing");
		while(true) {
			BookCopy bookCopy = BookCopiesService.getBookCopyById(ConsoleHelper.input("BookCopyId"));
			if(bookCopy!=null)
				return bookCopy;
			System.out.println("Book copy not found");
		}
	}

}
