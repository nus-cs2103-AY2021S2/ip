package com.nus.duke.command;

/**
 * ExitCommand class encapsulates the logic for processing a "exit" command.
 */
public class ExitCommand extends Command {

    public static final String COMMAND = "exit";

    @Override
    public String execute() {
        return "Exiting the application...";
    }
}
