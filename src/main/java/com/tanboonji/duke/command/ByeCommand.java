package com.tanboonji.duke.command;

public class ByeCommand extends Command {

    public static final String COMMAND = "bye";
    private final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public ByeCommand() {
    }

    @Override
    public boolean shouldExit() {
        return true;
    }

    @Override
    public boolean shouldSave() {
        return true;
    }

    @Override
    public String execute() {
        return EXIT_MESSAGE;
    }
}
