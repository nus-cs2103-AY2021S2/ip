package com.tjtanjin.ip;

import java.time.LocalDate;

/**
 * Entry point for handling logic and execution of add command.
 */
public class AddCommand {

    /**
     * Forwards the add task operation to TaskList.
     * @param taskType type of task (todo, deadline or event)
     * @param taskName name of task
     * @param taskDates array of dates
     */
    public void execute(TaskList taskList, String taskType, String taskName, LocalDate[] taskDates) {
        taskList.addTask(taskType, taskName, taskDates);
    }
}
