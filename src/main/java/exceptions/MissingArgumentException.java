package exceptions;

public class MissingArgumentException extends Exception {
    /**
     * Create missing argument exception with an error message
     * @param errorMessage
     */
    public MissingArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
