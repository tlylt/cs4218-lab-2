package cs4218.exceptions;

public abstract class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Formats the exception message for printing to an {@code OutputStream}.
     *
     * @return formatted string
     */
    public abstract String formatException();
}
