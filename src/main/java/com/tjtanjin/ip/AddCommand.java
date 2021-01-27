package com.tjtanjin.ip;

import java.time.LocalDate;

/**
 * Entry point for handling logic and execution of add command.
 */
public class AddCommand {

    private final TaskHandler taskHandler;

    /**
     * Constructor for AddCommand.
     * @param taskHandler provides access for command to modify tasks
     */
    public AddCommand(TaskHandler taskHandler) {
        this.taskHandler = taskHandler;
    }

    /**
     * Forwards the add task operation to TaskHandler.
     * @param taskType type of task (todo, deadline or event)
     * @param taskName name of task
     * @param taskDates array of dates
     * @return string response after operation is done
     */
    public String execute(String taskType, String taskName, LocalDate[] taskDates) {
        return taskHandler.addTask(taskType, taskName, taskDates);
    }
}
