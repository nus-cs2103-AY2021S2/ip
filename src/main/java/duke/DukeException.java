package duke;

/**
 * DukeException extends Exception and is thrown when handling errors
 */
public class DukeException extends Exception {
    private final String exception;

    /**
     * This is the constructor for a DukeException that takes in a String message.
     * @param exception This is the String error message.
     */
    public DukeException(String exception) {
        super(exception);
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "Omo... I'm sorry..." + exception;
    }
}
