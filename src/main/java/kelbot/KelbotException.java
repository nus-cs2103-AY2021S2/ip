package kelbot;

public class KelbotException extends Exception {
    /**
     * Initializes Kelbot Exception.
     * @param errorMessage The error message to be printed.
     */
    public KelbotException(String errorMessage) {
        super(errorMessage);
    }
}
