package models;

import services.BooksService;

public class BookCopy extends Model{
	private String id;
	private String bookId;
	
    public String getId() {
		return id;
	}
	
	public String getBookId() {
		return bookId;
	}
	
    public Book getBook() {
    	return BooksService.getBookById(bookId);
    }
     
    
}
