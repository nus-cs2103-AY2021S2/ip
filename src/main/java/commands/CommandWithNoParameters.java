package commands;

public abstract class CommandWithNoParameters extends Command {
    String tooManyArgumentsErrMsg = "Too many arguments provided. Did you mean '" + this.commandName + "'?";

    protected CommandWithNoParameters(String commandName, String commandBody) {
        super(commandName, commandBody);
    }
}
