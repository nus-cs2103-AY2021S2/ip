package duke;

/**
 *  DukeException class encapsulates information about a custom exception for Duke
 *  and inherits functionality from the Exception class
 */
public class DukeException extends Exception {
    private static final String TOP_BORDER = "_____________________________________________________________________";
    private static final String BTM_BORDER = "---------------------------------------------------------------------";
    private static final String PADDING = "  ";

    /**
     * Create a new DukeException to be thrown
     *
     * @param msg The error message
     */
    public DukeException(String msg) {
        super(msg);
    }

    /**
     * Returns a string representation of the DukeException
     * @return Error message in chat box
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
