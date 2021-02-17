package soonwee.duke;

import java.time.LocalDateTime;

/**
 * Represents a Task instance.
 */
public class Task {

    private String taskDesc;
    private boolean isCompleted;

    /**
     * Instantiates a task with its description.
     *
     * @param taskDesc task description.
     */
    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isCompleted = false;
    }

    public String getTaskDescription() {
        return this.taskDesc;
    }

    public boolean getCompleteStatus() {
        return this.isCompleted;
    }

    /**
     * Sets a task as completed.
     */
    public void setCompleted() {
        this.isCompleted = true;
    }

    /**
     * Gets datetime from task.
     *
     * @return datetime of task
     */
    public LocalDateTime getDateTime() {
        return null;
    }

    @Override
    public String toString() {
        String marked = new String();
        if (this.isCompleted) {
            marked = "X";
        } else {
            marked = " ";
        }
        return "[" + marked + "] " + this.taskDesc;
    }
}
