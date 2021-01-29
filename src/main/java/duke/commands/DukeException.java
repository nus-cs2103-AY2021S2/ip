package duke.commands;

/**
 * Represents exceptional behavior when executing Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException containing the input message.
     *
     * @param message The exception message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the DukeException message.
     *
     * @return The DukeException message.
     */
    @Override
    public String toString() {
        return getMessage();
    }
}
