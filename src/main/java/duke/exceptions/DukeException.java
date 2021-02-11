package duke.exceptions;

/**
 * class DukeException
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represents the exceptions in Duke
 */
public class DukeException extends Exception {
    /**
     * Constructor: creates a new DukeException
     * @param message the error message
     */
    public DukeException(String message) {
        super(message);
    }
}
