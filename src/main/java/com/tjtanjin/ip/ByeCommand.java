package com.tjtanjin.ip;

/**
 * Entry point for handling logic and execution of bye command.
 */
public class ByeCommand {

    private final String description;

    /**
     * Constructor for ByeCommand.
     */
    public ByeCommand(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Exits the program.
     */
    public void execute() {
        UiHandler.terminate("Good bye, see you soon! :D");
    }
}
