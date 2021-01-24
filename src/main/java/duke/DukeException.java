package duke;

/**
 * Handles all exception thrown by duke.
 */
public class DukeException extends Exception {

    /**
     * DukeException builder.
     *
     * @param message Exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}
