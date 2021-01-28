package duke.commands;

import static duke.utils.Messages.MESSAGE_HELP;

/**
 * Show help menu to user.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = "Command Word: " + COMMAND_WORD + "\n"
            + "Description: Print available commands\n"
            + "Usage: help";

    @Override
    public CommandResult execute() {
        String helpMessage = MESSAGE_HELP + "\n"
                + ByeCommand.MESSAGE_USAGE + "\n\n"
                + ListCommand.MESSAGE_USAGE + "\n\n"
                + ToDoCommand.MESSAGE_USAGE + "\n\n"
                + DeadlineCommand.MESSAGE_USAGE + "\n\n"
                + EventCommand.MESSAGE_USAGE + "\n\n"
                + DoneCommand.MESSAGE_USAGE + "\n\n"
                + DeleteCommand.MESSAGE_USAGE + "\n\n"
                + FindCommand.MESSAGE_USAGE + "\n\n"
                + HelpCommand.MESSAGE_USAGE;
        return new CommandResult(helpMessage);
    }
}
