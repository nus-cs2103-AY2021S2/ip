package duke;

import java.util.Objects;

/**
 * A task
 */
public class Task {

    protected String description;
    protected Boolean isDone;

    /**
     * Constructor for a task
     * @param description Details of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }
    /**
     * Marks the task as done
     */
    public void done() {
        this.isDone = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return getDescription().equals(task.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription());
    }

    /**
     * Displays the details of the task
     * @return Formatted string which shows the description and status of the task
     */
    @Override
    public String toString() {
        String done = this.isDone ? "\u2713" : "X";
        return "[" + done + "] " + this.description;
    }
}
