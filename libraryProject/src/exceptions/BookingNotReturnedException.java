package exceptions;
public class BookingNotReturnedException extends RuntimeException {
    public BookingNotReturnedException(String message) {
        super(message);
    }
}
