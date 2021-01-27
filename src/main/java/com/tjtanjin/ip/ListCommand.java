package com.tjtanjin.ip;

/**
 * Entry point for handling logic and execution of list command.
 */
public class ListCommand {

    private final TaskHandler taskHandler;
    private final String description;

    /**
     * Constructor for ListCommand.
     * @param taskHandler provides access for command to modify tasks
     */
    public ListCommand(String description, TaskHandler taskHandler) {
        this.description = description;
        this.taskHandler = taskHandler;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * List all tasks entered by user.
     * @return string response after operation is done
     */
    public String execute() {
        return taskHandler.showAllTasks();
    }
}
