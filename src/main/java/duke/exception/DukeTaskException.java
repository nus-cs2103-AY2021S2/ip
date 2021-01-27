package duke.exception;

/** An exception that happened due to chat bot trying to do something task related (eg. creating or removing a task) */
public class DukeTaskException extends DukeException{
    /**
     * Constructor for DukeTaskException
     * @param msg Error message that can be recalledv
     */
    public DukeTaskException(String msg) {
        super(msg);
    }
}
