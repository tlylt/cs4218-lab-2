package cs4218.exceptions;

public class RepException extends ApplicationException {
    public RepException(String message) {
        super(message);
    }

    public RepException(String message, Throwable throwable) {
        super(message, throwable);
    }

    @Override
    public String formatException() {
        return "rep: " + this.getMessage();
    }
}
