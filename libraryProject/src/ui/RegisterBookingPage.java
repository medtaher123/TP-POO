package ui;

import Managers.BookCopyManager;
import Managers.BookingManager;
import Managers.SettingsManager;
import Managers.UserManager;
import helpers.BarcodeScannerSimulator;
import models.BookCopy;
import models.Booking;
import models.User;

public class RegisterBookingPage extends BackOnlyPage {

	// TODO: class not tested
	@Override
	void printContent() {
		User user = BarcodeScannerSimulator.scanUser();
		if (user.getType() != User.UserType.MEMBER) {
			System.out.println("User " + user.getShortDisplay() + " is not a member (" + user.getType() + ")");
			return;
		}
		if (UserManager.haveUnreturnedLateBooks(user)) {
			System.out
					.println("User " + user.getShortDisplay() + " have unreturned Late books. Must return them first");
			return;
		}
		if (UserManager.reachedBookingLimit(user)) {
			System.out.println("User " + user.getShortDisplay() + " have reached the booking limit ("
					+ SettingsManager.getSettings().getBookingLimit() + " books). Must return the other books first");
			return;
		}

		BookCopy book = BarcodeScannerSimulator.scanBook();
		if (!BookCopyManager.isAvailable(book)) {
			System.out.println("Book " + book.getId() + " is already booked, How did you scan this book.\n"
					+ "If you think this is an error please contact an admin");
			return;
		}
		Booking booking = BookingManager.book(user, book);
		if (booking == null) {
			System.out.println("Coudln't book the book. The booking is not booking properly xd");
			return;
		}
		System.out.println("Booking Successful");

	}

	@Override
	protected String getTitle() {
		return "Register Booking";
	}

}
