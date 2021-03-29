package com.tjtanjin.steve.commands;

import com.tjtanjin.steve.tasks.TaskHandler;

/**
 * Entry point for handling logic and execution of done command.
 */
public class DoneCommand {

    private final TaskHandler TASK_HANDLER;
    private final String DESCRIPTION;

    /**
     * Constructor for DoneCommand.
     *
     * @param taskHandler provides access for command to modify tasks
     */
    public DoneCommand(String description, TaskHandler taskHandler) {
        this.DESCRIPTION = description;
        this.TASK_HANDLER = taskHandler;
    }

    /**
     * Gets the description of done command.
     *
     * @return description of done command
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Forwards the mark task as done operation to TaskHandler.
     *
     * @param index of task to mark as done
     * @return string response after operation is done
     */
    public String execute(int index) {
        return TASK_HANDLER.markTaskDone(index);
    }
}
