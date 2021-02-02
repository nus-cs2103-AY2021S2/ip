package duke.commands;

import duke.tasks.TaskList;

/**
 * Represents a user command.
 */
public abstract class Command {
    protected TaskList taskList;

    /**
     * Sets the current instance of task list that the command will operate on.
     *
     * @param taskList The current instance of task list.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command.
     *
     * @return Result command.
     */
    public abstract CommandResult execute();
}
