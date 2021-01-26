package duke.dukeexceptions;

/**
 * Custom Exception class to specify invalid Task stored in local file.
 */
public class InvalidTaskTypeException extends DukeException {
    public InvalidTaskTypeException() {
        super("Invalid task type!");
    }
}
