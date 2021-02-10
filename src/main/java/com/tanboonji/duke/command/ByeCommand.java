package com.tanboonji.duke.command;

/**
 * The ByeCommand class contains information to execute the "bye" command.
 */
public class ByeCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "bye";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Default class constructor.
     */
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
