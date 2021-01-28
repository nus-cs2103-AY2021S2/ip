package surrealchat.command;

import surrealchat.task.TaskManagement;

/**
 * Base class for user commands.
 */
public abstract class Command {
    protected final String commandType;

    /**
     * Creates new Command object.
     * @param commandType Type of command.
     */
    public Command(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Executes the user command to give a String output.
     * @param taskManagement TaskManagement object that handles Task objects relevant to command.
     * @return String to be printed.
     */
    public abstract String execute(TaskManagement taskManagement);
}
