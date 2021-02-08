package lihua.commands;

import lihua.commands.feedback.CommandResult;
import lihua.commons.Messages;

/**
 * Command class representing a command to exit the application.
 */
public class ExitCommand extends Command {
    /** Command help information for exit command */
    public static final String MESSAGE_USAGE = "bye: Exit the application. Data will be auto-saved.\n"
            + "---- Example: bye";

    /**
     * Initializes a new ExitCommand.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Executes the exit command.
     * Exit the application and give feedback to user.
     *
     * @return A CommandResult Object containing feedback to user.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(Messages.MESSAGE_INFORM_EXIT);
    }

    /**
     * Checks whether a command is of type ExitCommand.
     *
     * @param command The Command object to be checked.
     * @return True if the Command object is of type ExitCommand, false otherwise
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
