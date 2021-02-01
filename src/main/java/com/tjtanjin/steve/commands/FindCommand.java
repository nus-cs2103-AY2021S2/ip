package com.tjtanjin.steve.commands;

import com.tjtanjin.steve.tasks.TaskHandler;

/**
 * Entry point for handling logic and execution of find command.
 */
public class FindCommand {

    private final TaskHandler TASK_HANDLER;
    private final String DESCRIPTION;

    /**
     * Constructor for FindCommand.
     *
     * @param taskHandler provides access for command to modify tasks
     */
    public FindCommand(String description, TaskHandler taskHandler) {
        this.DESCRIPTION = description;
        this.TASK_HANDLER = taskHandler;
    }

    /**
     * Gets the description of find command.
     *
     * @return description of find command
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Forwards the find task operation to TaskHandler.
     *
     * @param taskName name of task
     * @return string response after operation is done
     */
    public String execute(String taskName) {
        return TASK_HANDLER.findTask(taskName.split(" "));
    }
}
