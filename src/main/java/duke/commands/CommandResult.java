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
     * Creates a {@code CommandResult} object without an updated task list.
     *
     * @param messageForUser message string to show to user
     */
    public CommandResult(String messageForUser, boolean isExiting) {
        this.messageForUser = messageForUser;
        updatedTaskList = null;
        this.isExiting = isExiting;
    }

    /**
     * Creates a {@code CommandResult} object with the given updated task list.
     *
     * @param messageForUser  message string to show to user
     * @param updatedTaskList updated task list after execution of a command
     */
    public CommandResult(String messageForUser, TaskList updatedTaskList, boolean isExiting) {
        this.messageForUser = messageForUser;
        this.updatedTaskList = updatedTaskList;
        this.isExiting = isExiting;
    }

    /**
     * Returns the message to be printed to the user.
     *
     * @return message for the user
     */
    public String getMessageForUser() {
        return messageForUser;
    }

    /**
     * Returns the updated task list after the execution of a command.
     *
     * @return updated task list
     */
    public TaskList getUpdatedTaskList() {
        return updatedTaskList;
    }
    
    public boolean isExitingProgram() {
        return isExiting;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof CommandResult) {
            CommandResult objCommandResult = (CommandResult) obj;
            if (!messageForUser.equals(objCommandResult.messageForUser)) {
                return false;
            }
            if (updatedTaskList != null && objCommandResult.updatedTaskList != null) {
                return updatedTaskList.equals(objCommandResult.updatedTaskList);
            } else {
                return updatedTaskList == null && objCommandResult.updatedTaskList == null;
            }
        } else {
            return false;
        }
    }
}
