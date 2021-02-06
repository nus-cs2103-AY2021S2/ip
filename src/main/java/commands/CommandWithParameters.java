package commands;

public abstract class CommandWithParameters extends Command {
    private String insufficientArgsErrorMessage;

    protected CommandWithParameters(String commandName, String commandBody) {
        super(commandName, commandBody);
    }
}
