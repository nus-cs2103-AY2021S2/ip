package lihua.commands;

import lihua.commons.Messages;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows application usage instructions.\n"
            + "---- Example: " + COMMAND_WORD;
    public boolean isAskingForHelp = true;

    public HelpCommand() {
        super();
    }

    public HelpCommand(boolean isAskingForHelp) {
        super();
        this.isAskingForHelp = isAskingForHelp;
    }

    @Override
    public CommandResult execute() {
        String feedback = isAskingForHelp
                ? Messages.MESSAGE_VIEW_COMMANDS
                : Messages.MESSAGE_GET_HELP;
        return new CommandResult(feedback);
    }
}
