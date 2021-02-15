package com.tanboonji.jhin.command;

/**
 * The ListCommand class contains information to execute the "list" command.
 */
public class ListCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "list";
    private static final String SUCCESS_MESSAGE = "Here are the tasks in your list:\n"
            + "%s";
    private static final String SUCCESS_EMPTY_ALIAS_MESSAGE = "You currently have 0 tasks.";

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
    public boolean shouldExitJhin() {
        return false;
    }

    @Override
    public String execute() {
        if (taskList.getSize() == 0) {
            return SUCCESS_EMPTY_ALIAS_MESSAGE;
        }

        return String.format(SUCCESS_MESSAGE, taskList);
    }
}
