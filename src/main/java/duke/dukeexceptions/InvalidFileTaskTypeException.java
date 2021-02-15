package duke.dukeexceptions;

/**
 * Models the exception to be thrown when there is a wrong task in the local storage file.
 */
public class InvalidFileTaskTypeException extends DukeException {
    public InvalidFileTaskTypeException() {
        super("Invalid task type in local file!");
    }
}
