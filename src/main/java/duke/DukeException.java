package duke;

/**
 * General Exception Handler specific to Duke.
 */
public class DukeException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Returns an exception with the specified detail message.
     *
     * @param message Exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}
