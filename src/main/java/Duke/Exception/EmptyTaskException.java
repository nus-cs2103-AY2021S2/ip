package Duke.Exception;

/**
 * An exception for Event, Deadline and Todo task with no description.
 */
public class EmptyTaskException extends Exception {
    private final String type;

    /**
     * The constructor for this Exception has 1 parameter: the type of the command (event, deadline or todo).
     * @param type Type of the command (event, deadline or todo)
     */
    public EmptyTaskException(String type) {
        this.type = type;
    }

    /**
     * Gets the message that user sees when entering a task with no description.
     * @return A message to the user.
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! The description of a " + type + " cannot be empty.";
    }
}
