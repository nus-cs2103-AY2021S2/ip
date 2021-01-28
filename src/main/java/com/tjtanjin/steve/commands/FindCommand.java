package com.tjtanjin.steve.commands;

import com.tjtanjin.steve.tasks.TaskHandler;

/**
 * Entry point for handling logic and execution of find command.
 */
public class FindCommand {

    private final TaskHandler taskHandler;
    private final String description;

    /**
     * Constructor for FindCommand.
     * @param taskHandler provides access for command to modify tasks
     */
    public FindCommand(String description, TaskHandler taskHandler) {
        this.description = description;
        this.taskHandler = taskHandler;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Forwards the find task operation to TaskHandler.
     * @param taskName name of task
     * @return string response after operation is done
     */
    public String execute(String taskName) {
        return taskHandler.findTask(taskName);
    }
}
