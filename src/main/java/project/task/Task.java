package project.task;

import java.time.LocalDateTime;

/**
 * Represents a task in the application.
 */
public class Task implements Comparable<Task> {
    /** The task description. */
    protected String description;
    /** The task status. */
    protected boolean isDone;
    /** If the task comes with a date and time of occurrence. */
    protected boolean hasSchedule;

    /**
     * Creates an instance of {@code Task} with empty task list.
     *
     * @param description The task description.
     */
    public Task(String description) {
        assert description.length() > 0;
        this.description = description;
        this.isDone = false;
        this.hasSchedule = false;
    }

    /**
     * Returns the task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns {@code String} representation of this task's status.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns if this task has a date and time of occurrence.
     */
    public boolean getIfScheduled() {
        return hasSchedule;
    }

    /**
     * Returns the date and time of occurrence if it exists.
     */
    public LocalDateTime getOccurrence() {
        return null;
    }

    /**
     * Changes this task's status to "done".
     */
    public void markAsDone() {
        this.isDone = true;
        assert this.getStatusIcon().equals("X");
    }

    /**
     * Returns {@code String} representation of this {@code Task}.
     */
    @Override
    public int compareTo(Task otherTask) {
        if (this.getOccurrence().isBefore(otherTask.getOccurrence())) {
            return -1;
        } else if (this.getOccurrence().isAfter(otherTask.getOccurrence())) {
            return 1;
        }
        return 0;
    }

    /**
     * Returns {@code String} representation of this {@code Task}.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}

