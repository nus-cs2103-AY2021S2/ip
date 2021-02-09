public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public ExitCommand() {}

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }

    @Override
    public CommandResult execute() throws DukeException {
        System.exit(0);
        return new CommandResult(EXIT_MESSAGE);
    }
}
