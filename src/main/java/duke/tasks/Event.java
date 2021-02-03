package duke.tasks;

import java.time.LocalDateTime;

public class Event extends Task {
    private static final String TYPE = "EVENT";
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description, TYPE);
        this.at = at;
    }

    public LocalDateTime getAt() {
        return at;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof  Event) {
            Event eTask = (Event) obj;
            return this.description.equals(eTask.getDescription())
                    && this.at.equals(eTask.getAt());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(at:" + at.format(super.DATE_TIME_FORMATTER) + ")";
    }
}