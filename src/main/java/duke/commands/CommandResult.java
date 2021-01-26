package duke.commands;

import duke.tasks.TaskList;

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
