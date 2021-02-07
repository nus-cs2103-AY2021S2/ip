package commands;

import exceptions.InvalidArgumentException;
import format.Ui;
import tasklist.TaskList;

public abstract class CommandWithNoParameters extends Command {
    String tooManyArgumentsErrMsg = "Too many arguments provided. Did you mean '" + this.commandName + "'?";

    protected CommandWithNoParameters(String commandName, String commandBody) {
        super(commandName, commandBody);
    }

    protected void handleTooManyArgs() {
        this.commandOutputMsg = Ui.formatException(
                new InvalidArgumentException(tooManyArgumentsErrMsg).getMessage()
        );
    }

}
