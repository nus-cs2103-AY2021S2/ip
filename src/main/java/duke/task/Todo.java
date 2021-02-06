package duke.task;

import java.time.LocalDateTime;

/**
 * Todo class which creates a todo task.
 */

public class Todo extends Task {
    private String taskType;

    /**
     * Creates Todo task which keeps track of task details.
     *
     * @param todoDetails of the todo task
     */
    public Todo(String todoDetails) {
        super(todoDetails);
        this.taskType = "todo";
    }

    /**
     * Method to get the type of task
     *
     * @return type of task
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Method to ammend task details
     *
     * @params newDetails to be updated
     */
    public void updateTaskDateAndTime(LocalDateTime newDateTime) {}

    @Override
    public String toString() {
        String toPrint = "[T]" + super.toString();
        assert !toPrint.isEmpty() : "Something should be printed.";
        return toPrint;
    }

    /**
     * Returns details of the todo task.
     *
     * @return details of the task
     */
    @Override
    public String getTaskDetails() {
        return super.toString();
    }
}
