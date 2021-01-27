package com.tjtanjin.ip;

/**
 * Entry point for handling logic and execution of bye command.
 */
public class ByeCommand {

    /**
     * Constructor for ByeCommand.
     */
    public ByeCommand() {}

    /**
     * Exits the program.
     */
    public void execute() {
        UiHandler.terminate("Good bye, see you soon! :D");
    }
}
