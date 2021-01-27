package duke.exception;

/** An exception that happened due to chat bot's execution */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException
     * @param msg Error message that can be recalled
     */
    public DukeException(String msg) {
        super(msg);
    }
}
