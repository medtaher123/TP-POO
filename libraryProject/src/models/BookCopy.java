package models;

import services.BooksService;

public class BookCopy extends Model{
	private String id;
	private String bookId;
    private boolean isAvailable;
	
    public String getId() {
		return id;
	}
	
	public String getBookId() {
		return bookId;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
	
    public Book getBook() {
    	return BooksService.getBookById(bookId);
    }
     
    
}
