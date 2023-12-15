package models;

import services.BookCopiesService;

public class Book extends Model {
	private String id;
	private String title;
	private String author;
	private String country;
	private String language;

	private String imageLink;
	private String description;

	private int pages;
	private int year;
	private String link;
	private String genre;
	//private boolean isAvailable;

	public String getId() {
		return id != null?id:"_";
	}
	
	public String getCountry() {
		return country;
	}

	public String getImageLink() {
		return imageLink;
	}

	public int getPages() {
		return pages;
	}

	public int getYear() {
		return year;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public String getAuthor() {
		return author;
	}

	public String getGenre() {
		return genre;
	}

	public boolean isAvailable() {
		return getNumberOfAvailableCopies()>0;
	}
	
	public int getNumberOfAvailableCopies() {
		return BookCopiesService.getAvailableCopiesOfBook(id).length;
	}

	/*public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}*/

	public String getShortDisplay() {
		return title + " - " + author;
	}


}
