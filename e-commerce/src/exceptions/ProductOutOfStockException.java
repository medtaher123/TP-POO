package exceptions;

public class ProductOutOfStockException extends UserException {
    public ProductOutOfStockException(String message) {
        super(message);
    }
}
