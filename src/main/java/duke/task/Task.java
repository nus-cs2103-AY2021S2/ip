package duke.task;

import java.time.LocalDate;

/**
 * Represents a task. A <code>Task</code> consist of
 * name, type (todo, event, deadline), status and deadline (if applicable).
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Task constructor for todo.
     *
     * @param name name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Getter method for status.
     *
     * @return the status of the <code>Task</code>
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Getter method for name.
     *
     * @return the name of the <code>Task</code>
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for status.
     * This method set the <code>Task</code> status to done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Getter method for isDone.
     *
     * @return the name of the <code>Task</code>
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a readable string representing the task that can
     * be used for outputting to user.
     *
     * @return a representative string of <code>Task</code>
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                "T",
                isDone ? "X" : " ",
                name);
    }

    /**
     * Returns a formatted representative of <code>Task</code>.
     * Fields of <code>Task</code> will be formatted with delimiters for
     * writing to text file.
     *
     * @return a string that is formatted for writing
     */
    public String toSaveFormat() {
        return String.format("%s | %s | %s",
                "T",
                isDone ? "1" : 0,
                this.name);
    }

    public LocalDate getDate() {
        return null;
    }
}
