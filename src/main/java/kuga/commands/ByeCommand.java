package kuga.commands;

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
        return new CommandResult(true);
    }
}
