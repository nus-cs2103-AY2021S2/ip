public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    private static final String EXIT_MESSAGE = "Exiting...";

    public ByeCommand() {
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(EXIT_MESSAGE);
    }
    
    public static boolean isByeCommand(Command command) {
        return command instanceof ByeCommand;
    }
}
