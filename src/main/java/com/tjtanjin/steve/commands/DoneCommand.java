package com.tjtanjin.steve.commands;

import com.tjtanjin.steve.tasks.TaskHandler;

/**
 * Entry point for handling logic and execution of done command.
 */
public class DoneCommand {

    private final TaskHandler taskHandler;
    private final String description;

    /**
     * Constructor for DoneCommand.
     * @param taskHandler provides access for command to modify tasks
     */
    public DoneCommand(String description, TaskHandler taskHandler) {
        this.description = description;
        this.taskHandler = taskHandler;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Forwards the mark task as done operation to TaskHandler.
     * @param index of task to mark as done
     * @return string response after operation is done
     */
    public String execute(int index) {
        return taskHandler.markTaskDone(index);
    }
}
