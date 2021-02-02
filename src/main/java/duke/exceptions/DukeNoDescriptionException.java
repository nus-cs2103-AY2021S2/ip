package duke.exceptions;

/**
 * Represents an exception when the command has an empty description.
 */
public class DukeNoDescriptionException extends DukeException {
    private final String command;

    /**
     * Constructs a DukeNoDescriptionException that contains the command type.
     * @param command string representation of the command type.
     */
    public DukeNoDescriptionException(String command) {
        this.command = command;
    }

    /**
     * Returns string representation for the cause of the DukeNoDescriptionException.
     * @return string representation for the cause of the exception.
     */
    public String toString() {
        return String.format("☹ OOPS!!! The description of a %s cannot be empty.", command);
    }
}
