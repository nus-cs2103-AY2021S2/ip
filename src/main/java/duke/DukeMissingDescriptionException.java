package duke;

public class DukeMissingDescriptionException extends DukeException {
    private String command;

    /**
     * Constructs missing description exception.
     * @param command Type of command which had the exception.
     */
    public DukeMissingDescriptionException(String command) {
        this.command = command;
    }

    /**
     * Returns string message of missing description exception.
     * @return String message of missing description exception.
     */
    @Override
    public String toString() {
        String missingMsg = String.format("     Sorry, '%s' task has missing description!\n",
                command);
        return missingMsg + HELP_MSG;
    }
}
