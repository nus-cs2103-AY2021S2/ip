package monica;
/**
 * Represents exceptions specified by Monica application.
 */
public class MonicaException extends Exception {
    protected String message;

    /**
     * Constructor for MonicaException class that handles exceptions.
     * @param message Error message.
     */
    public MonicaException(String message) {
        this.message = message;
    }

    /**
     * Generates output message when an exception is caught.
     */
    public String toString() {
        return ":< OOPS!!! " + message + "\nEnter 'help' to find out more.\n";
    }
}
