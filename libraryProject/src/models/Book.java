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
	// private boolean isAvailable;

	public String getId() {
		return id != null ? id : "_";
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
		return getNumberOfAvailableCopies() > 0;
	}

	public int getNumberOfAvailableCopies() {
		return BookCopiesService.getAvailableCopiesOfBook(id).length;
	}

	/*
	 * public void setAvailable(boolean isAvailable) { this.isAvailable =
	 * isAvailable; }
	 */

	public String getShortDisplay() {
		return title + " - " + author;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
