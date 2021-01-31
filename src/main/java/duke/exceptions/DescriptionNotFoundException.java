package duke.exceptions;

/**
 * Error class for no indication of task description.
 */
public class DescriptionNotFoundException extends DukeException {
    /**
     * Constructs a DescriptionNotFoundException with specific message for
     * no indication of task description.
     */
    public DescriptionNotFoundException() {
        super("Please provide description for your task.");
    }
}
