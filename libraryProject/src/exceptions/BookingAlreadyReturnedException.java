package exceptions;
public class BookingAlreadyReturnedException extends RuntimeException {
    public BookingAlreadyReturnedException(String message) {
        super(message);
    }
}
