package duke.commands;

import duke.tasks.TaskList;

/**
 * Represents the result of a command after execution.
 */
public class CommandResult {
    private String messageForUser;
    private TaskList updatedTaskList;

    public CommandResult(String messageForUser) {
        this.messageForUser = messageForUser;
        this.updatedTaskList = null;
    }

    public CommandResult(String messageForUser, TaskList updatedTaskList) {
        this.messageForUser = messageForUser;
        this.updatedTaskList = updatedTaskList;
    }

    /**
     * Returns the message to be printed to the user.
     * @return message for the user
     */
    public String getMessageForUser() {
        return messageForUser;
    }

    /**
     * Returns the updated task list after the execution of a command.
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
            if (!this.messageForUser.equals(objCommandResult.messageForUser)) {
                return false;
            }
            if (this.updatedTaskList != null && objCommandResult.updatedTaskList != null) {
                return this.updatedTaskList.equals(objCommandResult.updatedTaskList);
            } else {
                return this.updatedTaskList == null && objCommandResult.updatedTaskList == null;
            }
        } else {
            return false;
        }
    }
}
