package com.tjtanjin.steve.commands;

import com.tjtanjin.steve.tasks.TaskHandler;

/**
 * Entry point for handling logic and execution of delete command.
 */
public class DeleteCommand {

    private final TaskHandler TASK_HANDLER;
    private final String DESCRIPTION;

    /**
     * Constructor for DeleteCommand.
     *
     * @param taskHandler provides access for command to modify tasks
     */
    public DeleteCommand(String description, TaskHandler taskHandler) {
        this.DESCRIPTION = description;
        this.TASK_HANDLER = taskHandler;
    }

    /**
     * Gets the description of delete command.
     *
     * @return description of delete command
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Forwards the delete task operation to TaskHandler.
     *
     * @param index of task to delete
     * @return string response after operation is done
     */
    public String execute(int index) {
        return TASK_HANDLER.deleteTask(index);
    }
}
