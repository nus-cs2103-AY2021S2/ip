package duke.dukeexceptions;

/**
 * Models an exception to be thrown when the task list is empty when it is not supposed to be.
 */
public class EmptyListException extends DukeException {
    public EmptyListException() {
        super("Your TaskList is empty!");
    }
}
