package com.nus.duke.command;

public class ExitCommand extends Command {

    public static final String COMMAND = "exit";

    @Override
    public String execute() {
        return "Exiting the application...";
    }
}
