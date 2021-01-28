package com.tjtanjin.steve.commands;

import java.util.HashMap;

/**
 * Entry point for handling logic and execution of help command.
 */
public class HelpCommand {

    private final HashMap<String, String> cmdInfo;
    private final String description;

    /**
     * Constructor for HelpCommand.
     */
    public HelpCommand(String description, HashMap<String, String> cmdInfo) {
        this.description = description;
        this.cmdInfo = cmdInfo;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * List all available command usage and descriptions.
     * @return string response after operation is done
     */
    public String execute() {
        StringBuilder str = new StringBuilder("Info: The available commands are as listed below:");
        for (String info : cmdInfo.values()) {
            str.append("\n").append(info);
        }
        return str.toString();
    }
}
