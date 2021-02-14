package duke;

public class DukeWrongFormatException extends DukeException {
    private String command;

    /**
     * Constructs new duke wrong format exception.
     *
     * @param command Name of command which the exception occurs.
     */
    DukeWrongFormatException(String command) {
        this.command = command;
    }

    /**
     * Returns string format of exception message.
     *
     * @return String message of wrong format exception.
     */
    @Override
    public String toString() {
        String formatMsg = String.format("      '%s' command has wrong format!\n", command);
        return formatMsg + HELP_MSG;
    }
}
