package exceptions;

public class ConfigFileNotFound extends RuntimeException {
    public ConfigFileNotFound(String message) {
        super(message);
    }
}
