package pason.commands;

public class CommandResult {
    private String output;
    private CommandResultType commandResultType;

    /**
     *  Creates a command result object.
     */
    public CommandResult(String output, CommandResultType commandResultType) {
        this.output = output;
        this.commandResultType = commandResultType;
    }

    public String getOutput() {
        return this.output;
    }

    public CommandResultType getCommandResultType() {
        return this.commandResultType;
    }
}
