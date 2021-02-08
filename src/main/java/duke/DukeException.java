package duke;

/**
 *  DukeException class contains information of exceptions for duke
 *  Inherits from Exception class.
 */
public class DukeException extends Exception {

    /**
     * Create a new DukeException to be thrown.
     *
     * @param errorMessage String representation of the error
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
