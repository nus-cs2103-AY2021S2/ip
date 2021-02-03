package duke.commands;

import duke.tasks.TaskList;

/**
 * Represents the result of a command after execution.
 */
public class CommandResult {
    private final String messageForUser;
    private final TaskList updatedTaskList;
    private final boolean isExiting;

    /**
     * Creates a {@code CommandResult} object with a message for the user, without an updated task list,
     * and sets whether the application will be exiting after the command.
     *
     * @param messageForUser Message string to show to user.
     * @param isExiting Boolean that sets whether the application is exiting.
     */
    public CommandResult(String messageForUser, boolean isExiting) {
        this.messageForUser = messageForUser;
        updatedTaskList = null;
        this.isExiting = isExiting;
    }

    /**
     * Creates a {@code CommandResult} object with a message for the user and an updated task list,
     * and sets whether the application will be exiting after the command.
     *
     * @param messageForUser Message string to show to user.
     * @param updatedTaskList Updated task list after execution of a command.
     * @param isExiting Boolean that sets whether the application is exiting.
     */
    public CommandResult(String messageForUser, TaskList updatedTaskList, boolean isExiting) {
        this.messageForUser = messageForUser;
        this.updatedTaskList = updatedTaskList;
        this.isExiting = isExiting;
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

    /**
     * Compares this {@code CommandResult} to the specified object. The result if true if and only if the
     * argument is not null and is a {@code CommandResult} object that has the same message for user and
     * the same updated taskList.
     *
     * @param obj The object to compare this {@code CommandResult} against.
     * @return True if the given object represents a {@code CommandResult} equivalent to this CommandResult,
     *         false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommandResult)) {
            return false;
        }

        CommandResult objCommandResult = (CommandResult) obj;
        if (!messageForUser.equals(objCommandResult.messageForUser)) {
            return false;
        }
        if (updatedTaskList != null && objCommandResult.updatedTaskList != null) {
            return updatedTaskList.equals(objCommandResult.updatedTaskList);
        }
        return updatedTaskList == null && objCommandResult.updatedTaskList == null;
    }
}
