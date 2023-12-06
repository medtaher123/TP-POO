package models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Booking extends Model{
	public static List<Booking> bookingHistory= new ArrayList<Booking>();
    private int bookingId;
    private int userId;
    private int bookId;
    private Date bookingDate;
    private Date returnDate;
    // Other booking-related attributes

    // Methods for handling book borrowing, returning, fines, etc.
    // ...

    // Getters, setters, and other methods
    // ...
}
