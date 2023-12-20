package ui;

import authentification.AuthenticationSystem;
import helpers.BarcodeScannerSimulator;
import helpers.ConsoleHelper;
import models.BookCopy;
import models.Booking;
import services.BookingsService;

public class BookBookingHistoryPage extends BackOnlyPage {

	@Override
	void printContent() {
		BookCopy bc = BarcodeScannerSimulator.scanBook();
		Booking[] bookings;
		bookings = BookingsService.getBookCopyBookingHistory(bc.getId());
		ConsoleHelper.printNewLines(1);
		System.out.println("History:");
		if (bookings.length == 0)
			System.out.println("---no bookings---");
		for (Booking booking : bookings) {
			System.out.println(booking.getLongDisplay());
		}
	}

	@Override
	protected String getTitle() {
		return "Book Booking History";
	}

}
