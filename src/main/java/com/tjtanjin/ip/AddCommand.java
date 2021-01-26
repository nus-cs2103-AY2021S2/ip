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
    public static void execute(String taskType, String taskName, LocalDate[] taskDates) {
        TaskList.addTask(taskType, taskName, taskDates);
        Storage.saveTask(TaskList.getTasks().size(), "NEW",
                taskName, "incomplete", taskType.toUpperCase(), taskDates);
    }
}
