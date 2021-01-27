package duke.commands;

/**
 * Exits the application.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String EXIT_MESSAGE = "Exiting...";

    public ByeCommand() {
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(EXIT_MESSAGE);
    }

    /**
     * Checks if a command is an instance of ByeCommand.
     * @param command user command
     * @return true if command is an instance of ByeCommand, else false
     */
    public static boolean isByeCommand(Command command) {
        return command instanceof ByeCommand;
    }
}
