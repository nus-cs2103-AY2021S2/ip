package lihua.commands;

import lihua.commons.Messages;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exit the application. Data will be auto-saved.\n"
            + "---- Example: " + COMMAND_WORD;

    public ExitCommand() {
        super();
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(Messages.MESSAGE_INFORM_EXIT);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
