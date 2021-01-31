package duke.command;

/**
 * Place borders for each command given.
 */
public class CommandBorder implements ICommand {
    private ICommand decoratedCommand;

    /**
     * Generate Tasks depending on the type needed.
     *
     * @param decoratedCommand Command to be wrapped.
     */
    public CommandBorder(ICommand decoratedCommand) {
        this.decoratedCommand = decoratedCommand;
    }

    /**
     * Print out borders as well as executing the wrapped Command.
     *
     * @param parameters input needed to be passed to decoratedCommand.
     */
    public void execute(String parameters) {
        System.out.println("-".repeat(40));
        decoratedCommand.execute(parameters);
        System.out.println("-".repeat(40));
    }
}
