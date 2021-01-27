package duke;

/**
 * Self-defined DukeException to be thrown during execution.
 */
public class DukeException extends Exception {
    private String errorMessage;

    DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return "\t  OOPS!!! " + errorMessage;
    }
}
