package commands;

import exceptions.InvalidArgumentException;

public abstract class CommandWithNoParameters extends Command {
    final String tooManyArgumentsErrMsg = "Too many arguments provided. Did you mean '" + this.commandName + "'?";

    protected CommandWithNoParameters(String commandName, String commandBody) {
        super(commandName, commandBody);
    }

    protected void handleTooManyArgs() {
        handleException(new InvalidArgumentException(tooManyArgumentsErrMsg));
    }

}
