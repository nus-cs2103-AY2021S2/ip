package duke.exception;

/** An exception that happened due to chat bot trying to load data from hard disk */
public class DukeLoadException extends DukeException {
    /**
     * Constructor for DukeLoadException
     * @param msg Error message that can be recalled
     */
    public DukeLoadException(String msg) {
        super(msg);
    }
}
