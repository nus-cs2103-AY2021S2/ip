package com.tanboonji.duke.command;

/**
 * The ListCommand class contains information to execute the "list" command.
 */
public class ListCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "list";

    /**
     * Default class constructor.
     */
    public ListCommand() {
    }

    @Override
    public boolean shouldSaveData() {
        return false;
    }

    @Override
    public boolean shouldExitDuke() {
        return false;
    }

    @Override
    public String execute() {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list:\n");
        builder.append(taskList);
        return builder.toString().trim();
    }
}
