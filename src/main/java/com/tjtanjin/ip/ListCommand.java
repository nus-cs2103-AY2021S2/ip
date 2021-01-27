package com.tjtanjin.ip;

/**
 * Entry point for handling logic and execution of list command.
 */
public class ListCommand {
    /**
     * List all tasks entered by user.
     */
    public void execute(TaskHandler taskList) {
        taskList.showAllTasks();
    }
}
