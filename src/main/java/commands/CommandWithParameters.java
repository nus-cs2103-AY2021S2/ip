package commands;

import exceptions.MissingArgumentException;

public abstract class CommandWithParameters extends Command {
    protected static String insufficientArgsErrMsg = "There are missing arguments inputted for this %s command.";
    protected static String methodUsage; // todo can be used in err msg

    protected CommandWithParameters(String commandName, String commandBody) {
        super(commandName, commandBody);
    }

    protected void handleNoArgs() throws MissingArgumentException {
        throw new MissingArgumentException(String.format(insufficientArgsErrMsg, commandName));
    }
}
