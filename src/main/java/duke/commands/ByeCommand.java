package duke.commands;

import static duke.utils.Messages.MESSAGE_EXIT;

/**
 * Exits the application.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = "Command Word: " + COMMAND_WORD + "\n"
            + "Description: Exit the program\n"
            + "Usage: bye";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT, true);
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
