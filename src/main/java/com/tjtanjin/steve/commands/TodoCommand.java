package com.tjtanjin.steve.commands;

import com.tjtanjin.steve.tasks.TaskHandler;

/**
 * Entry point for handling logic and execution of todo command.
 */
public class TodoCommand {

    private final TaskHandler TASK_HANDLER;
    private final String DESCRIPTION;

    /**
     * Constructor for TodoCommand.
     *
     * @param taskHandler provides access for command to modify tasks
     */
    public TodoCommand(String description, TaskHandler taskHandler) {
        this.DESCRIPTION = description;
        this.TASK_HANDLER = taskHandler;
    }

    /**
     * Gets the description of todo command.
     *
     * @return description of todo command
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Forwards the add todo task operation to TaskHandler.
     *
     * @param taskType type of task (todo, deadline or event)
     * @param taskName name of task
     * @return string response after operation is done
     */
    public String execute(String taskType, String taskName) {
        return TASK_HANDLER.addTask(taskType, taskName, null);
    }
}
