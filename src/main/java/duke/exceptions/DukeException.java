package duke.exceptions;

/**
 * Duke Exception, which is the exception Duke Components will throw
 */

public class DukeException extends Exception {

    /**
     * Constructor for DukeException
     *
     * @param message Error message regarding the execption
     */
    public DukeException(String message) {
        super(message);
    }
}
