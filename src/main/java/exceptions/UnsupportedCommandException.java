package exceptions;

public class UnsupportedCommandException extends Exception {
    /**
     * Throws unsupported command exception with default message
     */
    public UnsupportedCommandException() {
        super("I don't recognise this command. Please try something else or enter help.");
    }
}


