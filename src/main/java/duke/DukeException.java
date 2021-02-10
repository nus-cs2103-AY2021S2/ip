package duke;

/**
 * Represents unchecked exceptions specific to Duke.
 */
public class DukeException extends RuntimeException {
    /**
     * Creates a RuntimeException specific to Duke.
     *
     * @param errorMessage Message when the exception is thrown.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
