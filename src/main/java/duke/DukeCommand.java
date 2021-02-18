package duke;

/**
 * Represents a Command and more details for that Command.
 */
public class DukeCommand {
    private final Command command;
    private final String details;

    /**
     * Creates a DukeCommand
     *
     * @param command a Command
     * @param details more details relevant to this Command
     */
    public DukeCommand(Command command, String details) {
        this.command = command;
        this.details = details;
    }

    /**
     * Retrieves the Command in this DukeCommand.
     *
     * @return the Command
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Retrieves the details in this DukeCommand.
     *
     * @return the details stored as a String
     */
    public String getDetails() {
        return details;
    }
}
