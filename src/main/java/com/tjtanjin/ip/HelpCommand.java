package com.tjtanjin.ip;

import java.util.HashMap;

/**
 * Entry point for handling logic and execution of help command.
 */
public class HelpCommand {

    /**
     * Constructor for HelpCommand.
     */
    public HelpCommand() {}

    /**
     * List all available command usage and descriptions.
     * @param cmdInfo mapping of each command and their information
     * @return string response after operation is done
     */
    public String execute(HashMap<String, String> cmdInfo) {
        StringBuilder str = new StringBuilder("Info: The available commands are as listed below:");
        for (String info : cmdInfo.values()) {
            str.append("\n").append(info);
        }
        return str.toString();
    }
}
