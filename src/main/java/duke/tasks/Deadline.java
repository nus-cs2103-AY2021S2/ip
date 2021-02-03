package duke.tasks;

import java.time.LocalDateTime;

/**
 * Deadline class that represents tasks with a deadline.
 */
public class Deadline extends Task{
    private static final String TYPE = "DEADLINE";
    protected LocalDateTime by;

    /**
     * Default constructor.
     * @param description string content of the deadlined task.
     * @param by deadline of task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TYPE);
        this.by = by;
    }

    /**
     * Returns deadline of task.
     * @return deadline of task.
     */
    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof  Deadline) {
            Deadline dTask = (Deadline) obj;
            return this.description.equals(dTask.getDescription())
                    && this.by.equals(((Deadline) dTask).getBy());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by:" + by.format(super.DATE_TIME_FORMATTER) + ")";
    }
 }
