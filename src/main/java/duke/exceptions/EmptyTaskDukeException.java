package duke.exceptions;

/**
 * Exception for when user enters a task with no task description
 */
public class EmptyTaskDukeException extends Exception {

    /**
     * Describes the error message when user enters task with no task description
     */
    public EmptyTaskDukeException() {
        super("Description of a task cannot be empty!\n"
                + "Please enter a valid input.");
    }
}
