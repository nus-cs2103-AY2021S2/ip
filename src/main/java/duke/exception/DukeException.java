package duke.exception;
/**
 * Customized exception class to display customized exception
 */
public class DukeException extends Exception {

    /** Create a customized exception
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }
}
