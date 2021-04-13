package duke;

/**
 * Exception where command does not exist for Duke.
 */
public class DukeWrongCommandException extends DukeException {
    private String command;

    /**
     * Constructs wrong command exception.
     *
     * @param command String format of command which is wrong.
     */
    public DukeWrongCommandException(String command) {
        this.command = command;
    }

    /**
     * Returns string message of wrong command exception.
     *
     * @return string message of wrong command exception.
     */
    @Override
    public String toString() {
        String wrongFormatMsg = String.format("Sorry, '%s' is not a proper command for Duke!\n",
                command);
        return wrongFormatMsg + HELP_MSG;
    }
}
