package az.dev.exception;

public class CustomException extends RuntimeException {

    private String message;

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }
}
