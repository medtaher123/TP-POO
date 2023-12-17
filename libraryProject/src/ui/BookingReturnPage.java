package ui;

import Managers.BarcodeScannerSimulator;
import Managers.BookCopyManager;
import Managers.BookingManager;
import Managers.SettingsManager;
import helpers.ConsoleHelper;
import models.BookCopy;
import models.Booking;

public class BookingReturnPage extends BackOnlyPage {

	@Override
	void printContent() {
		BookCopy book = BarcodeScannerSimulator.scanBook();
		if (BookCopyManager.isAvailable(book)) {
			System.out.println("Book " + book.getId() + " is not booked.\n"
					+ "If you think this is an error please contact an admin");
			return;
		}
		boolean isDamaged = ConsoleHelper.readBoolean("Is the book damaged?");
		Booking booking = BookingManager.returnBook(book,isDamaged);
		if (booking == null) {
			System.out.println("Coudln't return the book. The booking is not booking properly xd");
			return;
		}
		System.out.println("Return Successful.");
		handlePayement(booking);

	}

	private void handlePayement(Booking booking) {
		int damagefees = booking.isDamaged()?SettingsManager.getSettings().getDamagedBookFees():0;
		int lateReturnFees = booking.getReturnLatency()*SettingsManager.getSettings().getLateReturnFeesPerDay();
		int total = damagefees+lateReturnFees;
		if(total>0) {
			System.out.println("User Must Pay:");
			System.out.println("Damage Fees: "+damagefees);
			System.out.println("late return fees: " + booking.getReturnLatency() + " days * " + SettingsManager.getSettings().getLateReturnFeesPerDay() + "dt = "+ lateReturnFees + "dt");
			System.out.println("Total: "+total + "dt");
		}

		
	}

	@Override
	protected String getTitle() {
		return "Book Return Page";
	}

}
