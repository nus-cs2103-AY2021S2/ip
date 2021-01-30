package duke.exceptions;

/**
 * DukeException class is a class for all the exceptions thrown
 * during execution of the Duke program
 * It inherit from the Java exception class
 */
public class DukeException extends Exception {
    /**
     * DukeException constructor used to initialize the Exception
     *
     * @param message message of the exception
     */
    public DukeException(String message) {
        super(message);
    }

}
