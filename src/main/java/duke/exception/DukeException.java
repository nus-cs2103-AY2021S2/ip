package duke.exception;

public class DukeException extends Exception {
    /**
     * Constructor for DukeException
     * @param msg Error message that can be recalled
     */
    public DukeException(String msg) {
        super(msg);
    }
}
