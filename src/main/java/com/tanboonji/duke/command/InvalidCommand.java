package com.tanboonji.duke.command;

/**
 * The InvalidCommand class contains information to execute the "invalid" command.
 */
public class InvalidCommand extends Command {

    private final String errorMessage;

    /**
     * Class constructor specifying the error message that triggered the invalid command.
     *
     * @param errorMessage Error message that triggered the invalid command.
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

    @Override
    public boolean shouldSave() {
        return false;
    }

    @Override
    public String execute() {
        return errorMessage;
    }
}
