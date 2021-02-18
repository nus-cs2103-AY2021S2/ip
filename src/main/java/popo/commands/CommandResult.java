package popo.commands;

import popo.tasks.TaskList;

/**
 * Represents the result of a command after execution.
 */
public class CommandResult {
    private final TaskList updatedTaskList;
    private final boolean isExiting;
    private final String messageForUser;

    /**
     * Creates a {@code CommandResult} object with a message for the user, without an updated task list,
     * and sets whether the application will be exiting after the command.
     *
     * @param isExiting Boolean that sets whether the application is exiting.
     * @param messages  Arguments that represent the messages to show to user.
     */
    public CommandResult(boolean isExiting, String... messages) {
        updatedTaskList = null;
        this.isExiting = isExiting;
        StringBuilder fullMessage = new StringBuilder();
        for (String msg : messages) {
            fullMessage.append(msg);
        }
        messageForUser = fullMessage.toString();
    }

    /**
     * Creates a {@code CommandResult} object with a message for the user and an updated task list,
     * and sets whether the application will be exiting after the command.
     *
     * @param updatedTaskList Updated task list after execution of a command.
     * @param isExiting       Boolean that sets whether the application is exiting.
     * @param messages        Arguments that represent the messages to show to user.
     */
    public CommandResult(TaskList updatedTaskList, boolean isExiting, String... messages) {
        this.updatedTaskList = updatedTaskList;
        this.isExiting = isExiting;
        StringBuilder fullMessage = new StringBuilder();
        for (String msg : messages) {
            fullMessage.append(msg);
        }
        messageForUser = fullMessage.toString();
    }

    /**
     * Returns the message to be printed to the user.
     *
     * @return Message for the user.
     */
    public String getMessageForUser() {
        return messageForUser;
    }

    /**
     * Returns the updated task list after the execution of a command.
     *
     * @return The updated task list.
     */
    public TaskList getUpdatedTaskList() {
        return updatedTaskList;
    }

    /**
     * Returns whether the application is exiting after the command.
     *
     * @return True if application is exiting after command, otherwise false.
     */
    public boolean isExitingProgram() {
        return isExiting;
    }
}
