package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final LocalDateTime atDateTime;

    /**
     * Initializes an event task with a description and a datetime.
     *
     * @param description Description of the event task.
     * @param atDateTime  The event's time.
     */
    public Event(String description, LocalDateTime atDateTime) {
        super(description);
        this.atDateTime = atDateTime;
    }

    /**
     * Retrieves the event's time.
     *
     * @return The event's time.
     */
    public LocalDateTime getAtDateTime() {
        return this.atDateTime;
    }

    /**
     * Converts the event's time to a <code>String</code>.
     *
     * @return A formatted date string corresponding to the event's time.
     */
    public String getAtDateTimeString() {
        return this.getAtDateTime().format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
    }

    /**
     * Determines if the <code>Event</code> is overdue.
     *
     * @return True if the deadline is later than the current time, and false otherwise.
     */
    public boolean isOverdue() {
        if (this.isDone()) {
            return false;
        }
        return LocalDateTime.now().isAfter(this.getAtDateTime());
    }

    /**
     * Determines if the <code>Event</code> is soon.
     *
     * @param urgencyInDays Number of days to use when determining if the <code>Deadline</code>
     *                      is urgent.
     * @return True if the deadline is within <code>urgencyInDays</code> of the current time.
     */
    public boolean isUrgent(int urgencyInDays) {
        if (this.isDone()) {
            return false;
        }
        LocalDateTime urgencyMark = this.getAtDateTime().minusDays(urgencyInDays);
        return LocalDateTime.now().isAfter(urgencyMark) && !this.isOverdue();
    }
}
