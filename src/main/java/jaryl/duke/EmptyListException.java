package jaryl.duke;

/**
 * EmptyListException represents a duke exception
 * Handles empty list exceptions
 */
public class EmptyListException extends DukeException {
    /**
     * Constructor to instantiate a new EmptyListException
     */
    public EmptyListException() {
        super("It seems you have nothing on your list.");
    }
}
