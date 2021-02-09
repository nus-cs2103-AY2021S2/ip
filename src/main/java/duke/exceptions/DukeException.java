package duke.exceptions;

/**
 * Customized exception class for Duke exceptions
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException
     *
     * @param errorMessage errorMessage to be displayed
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
