package com.tjtanjin.steve.commands;

import com.tjtanjin.steve.tasks.TaskHandler;

/**
 * Entry point for handling logic and execution of list command.
 */
public class ListCommand {

    private final TaskHandler taskHandler;
    private final String description;

    /**
     * Constructor for ListCommand.
     *
     * @param taskHandler provides access for command to modify tasks
     */
    public ListCommand(String description, TaskHandler taskHandler) {
        this.description = description;
        this.taskHandler = taskHandler;
    }

    /**
     * Gets the description of list command, currently unused.
     *
     * @return description of list command
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Lists out all tasks entered by user.
     *
     * @return string response after operation is done
     */
    public String execute() {
        return taskHandler.showAllTasks();
    }
}
