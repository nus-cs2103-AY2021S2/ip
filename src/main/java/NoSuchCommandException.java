/**
 * Represents an exception which occurs when a command is invalid.
 */
public class NoSuchCommandException extends Exception {

    /**
     * Constructor for NoSuchCommandException.
     */
    NoSuchCommandException() {
        super("I'm sorry, but I don't know what that means :(");
    }
}
