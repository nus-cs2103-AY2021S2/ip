package duke.commands;

import static duke.utils.Messages.MESSAGE_HELP;

/**
 * Show help menu to user.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_HELP);
    }
}
