package com.lirc572.ip;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task (task with a deadline).
 */
public class DeadlineTask extends Task {

    /**
     * Format of datetime for parsing and printing.
     */
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * The deadline of the task.
     */
    private LocalDateTime dueTime;

    /**
     * Constructs a new DeadlineTask with the specified name.
     *
     * @param name The name of the task.
     */
    public DeadlineTask(String name) {
        super(name);
    }

    /**
     * Constructs a new DeadlineTask with the specified name and deadline.
     *
     * @param name The name of the task.
     * @param dueTime The deadline of the task.
     */
    public DeadlineTask(String name, String dueTime) {
        super(name);
        if (dueTime != null) {
            this.setDueTime(dueTime);
        }
    }

    /**
     * Sets the deadline of the DeadlineTask.
     *
     * @param dueTime The new deadline.
     */
    public void setDueTime(String dueTime) {
        this.dueTime = LocalDateTime.parse(dueTime, this.format);
    }

    /**
     * Returns the deadline of the DeadlineTask.
     *
     * @return The deadline of the DeadlineTask.
     */
    public String getDueTime() {
        return this.dueTime == null ? null : this.dueTime.format(this.format);
    }

    /**
     * Returns the string representation of the DeadlineTask for storage.
     *
     * @return The string representation of the DeadlineTask for storage.
     */
    @Override
    public String toSavedString() {
        return String.format(
                "D | %d | %s | %s",
                this.getIsDone() ? 1 : 0,
                this.getName(),
                this.getDueTime() == null ? "null" : this.getDueTime()
        );
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + (this.dueTime != null ? String.format(" (by: %s)", this.getDueTime()) : "");
    }
}
