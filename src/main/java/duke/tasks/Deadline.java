package duke.tasks;

import java.time.LocalDateTime;

public class Deadline extends Task{
    private static final String TYPE = "DEADLINE";
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, TYPE);
        this.by = by;
    }

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
