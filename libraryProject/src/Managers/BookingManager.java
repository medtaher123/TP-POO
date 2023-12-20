package Managers;



import java.util.Date;

import helpers.DateHelper;
import models.BookCopy;
import models.Booking;
import models.User;
import services.BookingsService;

public class BookingManager {
	
	public static Booking book(User user, BookCopy book) {
		Booking b = new Booking(user.getId(),book.getId(),new Date());
		b.setReturnDeadline(DateHelper.addDays(new Date(), SettingsManager.getSettings().getBookingMaxDuration()));
		return BookingsService.addBooking(b);
	}

	public static Booking returnBook(BookCopy book, boolean isDamaged) {
		Booking booking = BookingsService.getBooksActiveBooking(book.getId());
		booking.setReturnDate(new Date());
		booking.setDamaged(isDamaged);
		return BookingsService.updateBooking(booking);
	}
	
}
