package duke;

/**
 * Self-defined DukeException to be thrown during execution.
 */
public class DukeException extends Exception {
    private final String errorMessage;

    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return "OOPS!!! " + errorMessage;
    }
}
