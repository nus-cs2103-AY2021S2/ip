package com.tjtanjin.steve.tasks;

/**
 * ToDo is a child class of Task, retaining all the
 * same functionalities as its parent.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo class.
     *
     * @param taskName name of task
     * @param status task completion status
     */
    public ToDo(String taskName, String status) {
        super(taskName, status, "TODO");
    }

    /**
     * Marks todo task as completed.
     *
     * @return task with status marked as complete
     */
    @Override
    public Task markCompleted() {
        return new ToDo(super.getTaskName(), "complete");
    }
}
