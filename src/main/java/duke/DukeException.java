package duke;

/**
 * Represents a Exception specific to Duke.
 */
public class DukeException extends Exception {
    // private final String err;

    /**
     * Creates a DukeException
     *
     * @param err the Exception's message
     */
    public DukeException(String err) {
        super(err);
    }

    /**
     * String representation of a DukeException
     *
     * @return a String
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
