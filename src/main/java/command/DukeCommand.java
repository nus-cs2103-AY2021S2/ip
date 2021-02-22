package command;

/**
 * Represents a Command and more details for that Command.
 */
public class DukeCommand {
    private final Command command;
    private final CommandDetails details;

    /**
     * Creates a DukeCommand
     *
     * @param command a Command
     * @param details a CommandDetails that contains details relevant to this Command
     */
    public DukeCommand(Command command, CommandDetails details) {
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
     * @return the details represented as a CommandDetails
     */
    public CommandDetails getDetails() {
        return details;
    }
}
