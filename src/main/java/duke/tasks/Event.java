package duke.tasks;

import java.time.LocalDateTime;

public class Event extends Task {
    private static final String TYPE = "EVENT";
    protected LocalDateTime at;

    /**
     * Event class that represents event tasks.
     * @param description string content of event task.
     * @param at dateTime of event.
     */
    public Event(String description, LocalDateTime at) {
        super(description, TYPE);
        this.at = at;
    }

    /**
     * Returns date time of event.
     * @return dateTime of event.
     */
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