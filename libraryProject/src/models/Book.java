package models;

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

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getShortDisplay() {
		return title + " - " + author;
	}

	private String genre;
	private boolean isAvailable;

}
