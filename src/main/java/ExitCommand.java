public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public ExitCommand() {}

    /**
     * Check if command is a ExitCommand.
     * @param command command to be checked.
     * @return true if command is a ExitCommand; otherwise, false.
     */
    public static boolean isExit(Command command) {
        // instanceof returns false if it is null
        return command instanceof ExitCommand;
    }

    @Override
    public CommandResult execute() throws DukeException {
        return new CommandResult(EXIT_MESSAGE);
    }
}
