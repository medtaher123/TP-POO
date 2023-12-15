package services;

import java.util.Arrays;

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
        return gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.BOOKINGS_API_URL + "?" + queryParams), Booking[].class);
    }

    public static Booking[] getUsersBookingHistory(String userId) {
    	QueryParamsBuilder params = new QueryParamsBuilder();
		params.addQueryParam("userId", userId);
    	return getAllBookings(params.toString());
    }
    public static Booking[] getUsersActiveBookings(String userId) {
    	Object[] z = Arrays.stream(getUsersBookingHistory(userId))
    		    .filter(booking -> booking.getReturnDate() == null)
    		    .toArray();
    	//TODO: add to doc: filter in application because can't check for null in query params
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
        return gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.BOOKINGS_API_URL + "/" + id), Booking.class);
    }

    public static Booking updateBooking(Booking booking) {
        Gson gson = new Gson();
        return gson.fromJson(DatabaseService.sendHttpRequest("PUT", DatabaseService.BOOKINGS_API_URL + "/" + booking.getId(), booking), Booking.class);
    }

    public static Booking addBooking(Booking newBooking) {
        return new Gson().fromJson(DatabaseService.sendHttpRequest("POST", DatabaseService.BOOKINGS_API_URL, newBooking), Booking.class);
    }

    public static boolean deleteBookingById(String id) {
        DatabaseService.sendHttpRequest("DELETE", DatabaseService.BOOKINGS_API_URL + "/" + id);
        return true;
    }
    
    
    
    
}
