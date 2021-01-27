package duke.commands;

/**
 * Exits the application.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    private static final String MESSAGE_EXIT = "Exiting...";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT);
    }

    /**
     * Checks if a command is an instance of ByeCommand.
     *
     * @param command user command
     * @return true if command is an instance of ByeCommand, else false
     */
    public static boolean isByeCommand(Command command) {
        return command instanceof ByeCommand;
    }
}
