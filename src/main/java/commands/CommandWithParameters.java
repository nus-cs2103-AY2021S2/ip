package commands;

import exceptions.MissingArgumentException;

public abstract class CommandWithParameters extends Command {
    protected static String insufficientArgsErrorMessage = "There are missing arguments inputted for this command.";
    protected static String methodUsage;

    protected CommandWithParameters(String commandName, String commandBody) {
        super(commandName, commandBody);
    }

    protected void handleNoArgs() throws MissingArgumentException {
        throw new MissingArgumentException(insufficientArgsErrorMessage);
    }
}
