package duke.exception;

/** An exception that happened due to chat bot trying to save data to hard disk */
public class DukeSaveException extends DukeException {
    /**
     * Constructor for DukeSaveException
     * @param msg Error message that can be recalled
     */
    public DukeSaveException(String msg) {
        super(msg);
    }
}
