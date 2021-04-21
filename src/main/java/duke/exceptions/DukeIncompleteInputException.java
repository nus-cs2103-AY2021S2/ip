package duke.exceptions;

/**
 * DukeIncompleteInputException class is a class for all the exceptions thrown
 * Caused by a command that does not add a task to the task list
 * during execution of the Duke program
 * E.g. find, check. done
 *
 * It inherit from the Java DukeException class
 */
public class DukeIncompleteInputException extends DukeException {
    /**
     * DukeException constructor used to initialize the Exception
     *
     * @param action causing the exception to be thrown
     */
    public DukeIncompleteInputException(String action) {
        super(action);
    }

}
