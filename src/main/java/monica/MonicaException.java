package monica;
/**
 * Represents exceptions specified by Duke application.
 */
public class MonicaException extends Exception {
    protected String message;

    /**
     * Constructor for DukeException class that handles exceptions.
     * @param message Error message.
     */
    public MonicaException(String message) {
        this.message = message;
    }

    /**
     * Generates output message when an exception is caught.
     */
    public String toString() {
        return ":< OOPS!!! " + message;
    }
}
