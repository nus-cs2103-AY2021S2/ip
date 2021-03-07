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

    /**
     * getMessage: retrieves error message
     * @return errorMessage
     */
    @Override
    public String getMessage() {
        String errorMessage = "OOPS!!! " + super.getMessage();
        return errorMessage;
    }
}
