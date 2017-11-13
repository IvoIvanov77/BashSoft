package bg.softuni.exceptions;

public class DataAlreadyInitializedException extends RuntimeException {

    private static final String DATA_ALREADY_INITIALIZED = "Data is already initialized.";

    public DataAlreadyInitializedException() {
        super(DATA_ALREADY_INITIALIZED);
    }

    public DataAlreadyInitializedException(String message) {
        super(message);
    }
}
