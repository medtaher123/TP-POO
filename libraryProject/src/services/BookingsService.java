package services;

import java.util.Arrays;
import java.util.function.Predicate;

import com.google.gson.Gson;

import helpers.QueryParamsBuilder;
import models.Booking;
import models.User;

public class BookingsService extends DatabaseService {

	public static Booking[] getAllBookings() {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.BOOKINGS_API_URL), Booking[].class);
	}

	public static Booking[] getAllBookings(String queryParams) {
		Gson gson = new Gson();
		return gson.fromJson(
				DatabaseService.sendHttpRequest("GET", DatabaseService.BOOKINGS_API_URL + "?" + queryParams),
				Booking[].class);
	}

	public static Booking[] getUsersBookingHistory(String userId) {
		QueryParamsBuilder params = new QueryParamsBuilder();
		params.addQueryParam("userId", userId);
		return getAllBookings(params.toString());
	}

	public static Booking[] getBookCopyBookingHistory(String bookCopyId) {
		QueryParamsBuilder params = new QueryParamsBuilder();
		params.addQueryParam("bookId", bookCopyId);
		return getAllBookings(params.toString());
	}

	public static Booking[] getUsersActiveBookings(String userId) {
		return filter(getUsersBookingHistory(userId), booking -> !booking.isReturned());
	}

	public static Booking[] getUsersActiveLateBookings(String userId) {
		return filter(getUsersBookingHistory(userId), booking -> (!booking.isReturned()) && booking.isLate());
	}

	public static Booking getBooksActiveBooking(String bookId) {
		Booking[] b = filter(getBookCopyBookingHistory(bookId), booking -> !booking.isReturned());
		return b.length == 0 ? null : b[0];
	}

	private static Booking[] filter(Booking[] bookings, Predicate<? super Booking> predicate) {
		Object[] z = Arrays.stream(bookings).filter(predicate).toArray();
		// TODO: add to doc: filter in application because can't check for null in query
		// params
		Booking[] filteredBookings;
		if (z != null) {
			filteredBookings = Arrays.copyOf(z, z.length, Booking[].class);
		} else {
			filteredBookings = new Booking[0];
		}
		return filteredBookings;
	}

	public static Booking getBookingById(String id) {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.BOOKINGS_API_URL + "/" + id),
				Booking.class);
	}

	public static Booking updateBooking(Booking booking) {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("PUT",
				DatabaseService.BOOKINGS_API_URL + "/" + booking.getId(), booking), Booking.class);
	}

	public static Booking addBooking(Booking newBooking) {
		return new Gson().fromJson(
				DatabaseService.sendHttpRequest("POST", DatabaseService.BOOKINGS_API_URL, newBooking), Booking.class);
	}

	public static boolean deleteBookingById(String id) {
		DatabaseService.sendHttpRequest("DELETE", DatabaseService.BOOKINGS_API_URL + "/" + id);
		return true;
	}

}
