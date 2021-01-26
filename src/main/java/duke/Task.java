package duke;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns statusIcon in the form of 1 or 0.
     * 1 means done, 0 means undone.
     *
     * @return String representation of statusicon.
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns String representation of task description
     *
     * @return String of description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a task as done by changing the boolean value isDone to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String saveToFile() {
        return " | " + getStatusIcon() + " | " + getDescription();
    }

    public String toString() {
        return " | " + getStatusIcon() + " | " + getDescription();
    }
}
