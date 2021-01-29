package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event extends from class Task and represent a task
 * that is happening in a particular time/date.
 */
public class Event extends Task{
    private final String preposition;
    private final LocalDate date;

    /**
     * Returns an Event object that represent the task.
     *
     * @param description Description of the event.
     * @param preposition Preposition used by the user (eg at, by).
     * @param date Date of the event happening.
     */
    public Event(String description, String preposition, LocalDate date) {
        super(description);
        this.preposition = preposition;
        this.date =date;
    }

    /**
     * Returns a string representation of the event to be stored in the
     * hard disk.
     *
     * @return A String representing the task (in the form of "event + description + |
     * + preposition + date".
     */
    @Override
    String toFileString() {
        return String.format("event %s | %s %s", description,
                preposition, date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Override
    public String toString(){
        String dateStr = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[E][%s] %s (%s %s)", getStatusIcon(), description, preposition, dateStr);
    }
}
