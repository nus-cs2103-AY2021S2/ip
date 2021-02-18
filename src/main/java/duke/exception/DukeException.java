package duke.exception;

/**
 * A class of Exceptions unique to Duke
 */
public class DukeException extends Exception {

    /**
     * Creates a new DukeException
     * @param str an error message
     */
    public DukeException(String str) {
        super(str);
    }
}
