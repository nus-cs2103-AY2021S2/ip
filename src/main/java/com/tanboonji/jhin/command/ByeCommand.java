package com.tanboonji.jhin.command;

/**
 * The ByeCommand class contains information to execute the "bye" command.
 */
public class ByeCommand extends Command {

    private static final String SUCCESS_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Default class constructor.
     */
    public ByeCommand() {
    }

    @Override
    public boolean shouldSaveData() {
        return true;
    }

    @Override
    public boolean shouldExitJhin() {
        return true;
    }

    @Override
    public String execute() {
        return SUCCESS_MESSAGE;
    }
}
