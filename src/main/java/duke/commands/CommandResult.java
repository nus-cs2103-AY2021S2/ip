package duke.commands;

import duke.tasks.TaskList;

/**
 * Represents the result of a command after execution.
 */
public class CommandResult {
    private final String messageForUser;
    private final TaskList updatedTaskList;

    public CommandResult(String messageForUser) {
        this.messageForUser = messageForUser;
        updatedTaskList = null;
    }

    public CommandResult(String messageForUser, TaskList updatedTaskList) {
        this.messageForUser = messageForUser;
        this.updatedTaskList = updatedTaskList;
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
