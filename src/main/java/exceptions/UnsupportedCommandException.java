package exceptions;

public class UnsupportedCommandException extends Exception {
    public UnsupportedCommandException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public UnsupportedCommandException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Throws unsupported command exception with default message
     */
    public UnsupportedCommandException() {
        super("I don't recognise this command. Please try something else.");
    }
}


