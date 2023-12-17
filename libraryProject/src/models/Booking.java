package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import exceptions.BookingAlreadyReturnedException;
import exceptions.BookingNotReturnedException;
import helpers.DateHelper;
import services.BookCopiesService;
import services.BookingsService;
import services.BooksService;

public class Booking extends Model {
	private String id;
	private String userId;
	private String bookId;
	private Date bookingDate;
	private Date returnDeadline;
	private Date returnDate;
	private boolean isDamaged;
	
	public Booking(String userId, String bookId, Date bookingDate) {
		this.userId = userId;
		this.bookId = bookId;
		this.bookingDate = bookingDate;
	}

	/*public static Booking newBooking(String userId, String bookId) {
		return BookingsService.addBooking(new Booking(userId, bookId, new Date()));
	}*/// TODO: add to doc: created a static method to create booking handling the
		// instantiation process internally without exposing the constructor to add
		// additional logic before initialization and because the final resulting object
		// is the result of a request from the server (this allows the automatic
		// creation of an unique id and using it in the newly created object)

	public boolean isDamaged() {
		return isDamaged;
	}

	public void setDamaged(boolean isDamaged) {
		this.isDamaged = isDamaged;
	}

	public Date getReturnDeadline() {
		return returnDeadline;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Booking returnBooking() {
		if (returnDate != null) {
			throw new BookingAlreadyReturnedException(
					"book has already been returned.\nbookingId=" + id + " bookId: " + bookId);
		}
		returnDate = new Date();
		return BookingsService.updateBooking(this);
	}

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getBookId() {
		return bookId;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}
	
	public boolean isReturned() {
		return returnDate!=null;
	}
	
	public BookCopy getBookCopy() {
		return BookCopiesService.getBookCopyById(bookId);		
	}
	public Book getBook() {
		return BooksService.getBookById(getBookCopy().getBookId());
	}
	public boolean isLate() {
		if(isReturned())
			return returnDeadline.compareTo(returnDate)<0;
		return  returnDeadline.compareTo(DateHelper.getTodayStartOfDay())<0;
	}
	public int getReturnLatency() {
		Date date = returnDate!=null?returnDate:DateHelper.getTodayStartOfDay();
		long differenceInMillis = date.getTime() - returnDeadline.getTime();
        return (int) TimeUnit.MILLISECONDS.toDays(differenceInMillis);
	}
	
	public String getStatus() {
		if(isReturned())
			return "returned " + DateHelper.format(returnDate) + " | " + (isLate()? getReturnLatency() + " days late" : "returned on time" ) + " | status " + (isDamaged?"DAMAGED":"not damaged");
		return "notReturned " + " | deadline " + DateHelper.format(returnDeadline) + " | " + (isLate()? getReturnLatency() + " days late" : "returned on time" );
	}
	
	public String getLongDisplay() {
		return DateHelper.format(bookingDate) + " | " + getBook().getShortDisplay() + " | " + getStatus();
	}

	public void setReturnDeadline(Date deadline) {
		returnDeadline = deadline;		
	}

}
