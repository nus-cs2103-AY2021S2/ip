package com.nus.duke.command;

import com.nus.duke.data.TaskList;

/**
 * Abstract class that represents a command entered by the user.
 */
public abstract class Command {

    protected TaskList taskList;

    /**
     * Sets the execution context of this command.
     *
     * @param taskList List of Tasks
     */
    public void setContext(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command and returns a result message.
     *
     * @return Result message to be displayed
     */
    public abstract String execute();
}
