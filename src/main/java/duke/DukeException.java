package duke;

/**
 * Self-defined DukeException to be thrown during execution.
 */
public class DukeException extends Exception {
    private final String errorMessage;

    /**
     * Constructs the DukeException.
     *
     * @param errorMessage exception message
     */
    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns string format of the exception.
     *
     * @return string format of the exception
     */
    @Override
    public String toString() {
        return "OOPS!!! " + errorMessage;
    }
}
