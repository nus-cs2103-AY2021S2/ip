package duke.commands;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    private static final String MESSAGE_EXIT = "Exiting...";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT);
    }

    public static boolean isByeCommand(Command command) {
        return command instanceof ByeCommand;
    }
}
