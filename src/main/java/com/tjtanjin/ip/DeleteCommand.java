package com.tjtanjin.ip;

import java.time.LocalDate;

/**
 * Entry point for handling logic and execution of delete command.
 */
public class DeleteCommand {
    /**
     * Forwards the delete task operation to TaskList.
     * @param index of task to delete
     */
    public static void execute(int index) {
        Task task = TaskList.getTasks().get(index);
        TaskList.deleteTask(index);
        String taskName = task.getTaskName();
        String taskType = task.getType().toUpperCase();
        LocalDate taskDate = task.getDate();
        Storage.saveTask(index, "DELETE", taskName, "complete", taskType, taskDate);
    }
}
