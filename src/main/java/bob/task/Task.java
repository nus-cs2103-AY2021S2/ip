package bob.task;

import java.time.LocalDateTime;

/**
 * Represents a task
 */
public class Task {
    protected String name;
    protected boolean isDone = false;

    /**
     * Constructor of a task
     * @param name Name of task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Constructor of a task
     * @param name Name of task
     * @param isDone Status of task
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getType() {
        return "Task";
    }

    public LocalDateTime getDateTime() {
        return null;
    }

    /**
     * Updates the status of the task
     * @param isDone The boolean value representing the status of the task
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Prints the details of a task, including the status and name.
     * @return A string representing a task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
