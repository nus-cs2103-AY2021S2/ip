package lihua.commands;

import lihua.commands.feedback.CommandResult;
import lihua.commons.Messages;

/**
 * Command class representing a command to get help.
 */
public class HelpCommand extends Command {
    /** Command help information for help command */
    public static final String MESSAGE_USAGE = "help: Shows application usage instructions.\n"
            + "---- Example: help";
    private boolean isAskingForHelp;


    /**
     * Initializes a new HelpCommand with additional boolean parameter.
     *
     * @param isAskingForHelp True if the user is really asking for help,
     *                        false if the user is confused with commands
     *                        and the application prompts the user to get help.
     */
    public HelpCommand(boolean isAskingForHelp) {
        super();
        this.isAskingForHelp = isAskingForHelp;
    }

    /**
     * Executes the help command. If the user is really asking for help then
     * give user the help instructions. If the user is confused with commands then
     * prompts the user to get help.
     *
     * @return A CommandResult Object containing feedback to user.
     */
    @Override
    public CommandResult execute() {
        String feedback = isAskingForHelp
                ? Messages.MESSAGE_VIEW_COMMANDS
                : Messages.MESSAGE_GET_HELP;
        return new CommandResult(feedback);
    }
}
