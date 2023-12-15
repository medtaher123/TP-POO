package ui;

import authentification.AuthenticationSystem;
import models.Book;
import models.Booking;
import services.BookingsService;
import services.BooksService;

public class BookingListPage extends BackOnlyPage {

	private boolean showHistory = false;

	public BookingListPage() {
	}

	public BookingListPage(boolean showHistory) {
		this.showHistory = showHistory;
	}

	@Override
	void printContent() {
		Booking[] bookings;
		if (showHistory) {
			bookings = BookingsService.getUsersBookingHistory(AuthenticationSystem.getActiveUser().getId());
		} else {
			bookings = BookingsService.getUsersActiveBookings(AuthenticationSystem.getActiveUser().getId());
		}
		if (bookings.length == 0)
			System.out.println("---no bookings---");
		for (Booking booking : bookings) {
			System.out.println(booking.getLongDisplay());
		}

	}

	@Override
	protected String getTitle() {
		return showHistory ? "Booking History" : "Active Bookings";
	}

}
