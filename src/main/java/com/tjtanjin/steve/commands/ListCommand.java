package com.tjtanjin.steve.commands;

import com.tjtanjin.steve.tasks.TaskHandler;

/**
 * Entry point for handling logic and execution of list command.
 */
public class ListCommand {

    private final TaskHandler TASK_HANDLER;
    private final String DESCRIPTION;

    /**
     * Constructor for ListCommand.
     *
     * @param taskHandler provides access for command to modify tasks
     */
    public ListCommand(String description, TaskHandler taskHandler) {
        this.DESCRIPTION = description;
        this.TASK_HANDLER = taskHandler;
    }

    /**
     * Gets the description of list command, currently unused.
     *
     * @return description of list command
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Lists out all tasks entered by user.
     *
     * @return string response after operation is done
     */
    public String execute() {
        return TASK_HANDLER.showAllTasks();
    }
}
