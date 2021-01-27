package com.tjtanjin.ip;

/**
 * Entry point for handling logic and execution of list command.
 */
public class ListCommand {

    private final TaskHandler taskHandler;

    /**
     * Constructor for ListCommand.
     * @param taskHandler provides access for command to modify tasks
     */
    public ListCommand(TaskHandler taskHandler) {
        this.taskHandler = taskHandler;
    }
    /**
     * List all tasks entered by user.
     */
    public void execute() {
        taskHandler.showAllTasks();
    }
}
