package com.tjtanjin.ip;

/**
 * Entry point for handling logic and execution of delete command.
 */
public class DeleteCommand {
    /**
     * Forwards the delete task operation to TaskList.
     * @param index of task to delete
     */
    public void execute(TaskList taskList, int index) {
        taskList.deleteTask(index);
    }
}
