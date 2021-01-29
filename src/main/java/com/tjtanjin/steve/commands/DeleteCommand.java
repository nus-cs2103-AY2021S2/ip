package com.tjtanjin.steve.commands;

import com.tjtanjin.steve.tasks.TaskHandler;

/**
 * Entry point for handling logic and execution of delete command.
 */
public class DeleteCommand {

    private final TaskHandler taskHandler;
    private final String description;

    /**
     * Constructor for DeleteCommand.
     *
     * @param taskHandler provides access for command to modify tasks
     */
    public DeleteCommand(String description, TaskHandler taskHandler) {
        this.description = description;
        this.taskHandler = taskHandler;
    }

    /**
     * Gets the description of delete command.
     *
     * @return description of delete command
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Forwards the delete task operation to TaskHandler.
     *
     * @param index of task to delete
     * @return string response after operation is done
     */
    public String execute(int index) {
        return taskHandler.deleteTask(index);
    }
}
