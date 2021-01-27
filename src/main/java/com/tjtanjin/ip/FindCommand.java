package com.tjtanjin.ip;

/**
 * Entry point for handling logic and execution of find command.
 */
public class FindCommand {

    /**
     * Forwards the find task operation to TaskList.
     */
    public void execute(TaskList taskList, String taskName) {
        taskList.findTask(taskName);
    }
}
