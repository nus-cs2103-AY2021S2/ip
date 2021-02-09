package duke.dukeexceptions;

/**
 * Custom Exception class to specify invalid Task stored in local file.
 */
public class InvalidFileTaskTypeException extends DukeException {
    public InvalidFileTaskTypeException() {
        super("Invalid task type in local file!");
    }
}
