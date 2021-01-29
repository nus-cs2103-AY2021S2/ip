package com.tjtanjin.steve.commands;

import java.time.LocalDate;

import com.tjtanjin.steve.tasks.TaskHandler;

/**
 * Entry point for handling logic and execution of deadline command.
 */
public class DeadlineCommand {

    private final TaskHandler TASK_HANDLER;
    private final String DESCRIPTION;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param taskHandler provides access for command to modify tasks
     */
    public DeadlineCommand(String description, TaskHandler taskHandler) {
        this.DESCRIPTION = description;
        this.TASK_HANDLER = taskHandler;
    }

    /**
     * Gets the description of deadline command.
     *
     * @return description of deadline command
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Forwards the add deadline task operation to TaskHandler.
     *
     * @param taskType type of task (todo, deadline or event)
     * @param taskName name of task
     * @param taskDates array of dates
     * @return string response after operation is done
     */
    public String execute(String taskType, String taskName, LocalDate[] taskDates) {
        return TASK_HANDLER.addTask(taskType, taskName, taskDates);
    }
}
