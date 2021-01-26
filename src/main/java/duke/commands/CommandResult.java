package duke.commands;

import duke.tasks.TaskList;

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

    public String getMessageForUser() {
        return messageForUser;
    }

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
