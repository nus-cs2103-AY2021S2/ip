package duke;

/**
 * Represents unchecked exceptions specific to DukeObjects.Duke.
 */
public class DukeException extends RuntimeException {
    /**
     * Creates a RuntimeException specific to DukeObjects.Duke.
     *
     * @param errorMessage Message when the exception is thrown.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
