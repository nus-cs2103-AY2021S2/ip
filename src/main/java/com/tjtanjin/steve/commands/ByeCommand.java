package com.tjtanjin.steve.commands;

/**
 * Entry point for handling logic and execution of bye command.
 */
public class ByeCommand {

    private final String DESCRIPTION;

    /**
     * Constructor for ByeCommand.
     */
    public ByeCommand(String description) {
        this.DESCRIPTION = description;
    }

    /**
     * Gets the description of bye command, currently unused.
     *
     * @return description of bye command
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Exits the program.
     */
    public String execute() {
        return "Terminated: Good bye, see you soon! :D";
    }
}
