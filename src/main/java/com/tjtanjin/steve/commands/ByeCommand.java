package com.tjtanjin.steve.commands;

import com.tjtanjin.steve.ui.UiHandler;

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

    /**
     * Gets the description of bye command, currently unused.
     *
     * @return description of bye command
     */
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
