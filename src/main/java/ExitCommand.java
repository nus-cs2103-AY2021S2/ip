public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public ExitCommand() {}

    public static boolean isExit(Command command) {
        // instanceof returns false if it is null
        return command instanceof ExitCommand;
    }

    @Override
    public CommandResult execute() throws DukeException {
        return new CommandResult(EXIT_MESSAGE);
    }
}
