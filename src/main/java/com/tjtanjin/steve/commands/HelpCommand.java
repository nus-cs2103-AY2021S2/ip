package com.tjtanjin.steve.commands;

import java.util.HashMap;

/**
 * Entry point for handling logic and execution of help command.
 */
public class HelpCommand {

    private final HashMap<String, String> CMD_INFO;
    private final String DESCRIPTION;

    /**
     * Constructor for HelpCommand.
     */
    public HelpCommand(String description, HashMap<String, String> cmdInfo) {
        this.DESCRIPTION = description;
        this.CMD_INFO = cmdInfo;
    }

    /**
     * Gets the description of help command, currently unused.
     *
     * @return description of help command
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Lists out all available command usage and descriptions.
     *
     * @return string response after operation is done
     */
    public String execute() {
        StringBuilder str = new StringBuilder("Info: The available commands are as listed below:");
        for (String info : CMD_INFO.values()) {
            str.append("\n").append(info);
        }
        return str.toString();
    }
}
