public class MissingArgumentException extends Exception {
    /**
     * Create missing argument exception with an error message and a throwable
     * @param errorMessage
     */
    public MissingArgumentException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    /**
     * Create missing argument exception with an error message
     * @param errorMessage
     */
    public MissingArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
