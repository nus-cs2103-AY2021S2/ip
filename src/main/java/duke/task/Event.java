package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class is a type of Task and represents the
 * abstraction of an event which occurs at a certain date.
 */
public class Event extends Task {
    public static final String ENCODED_TYPE = "E";
    protected LocalDate date;

    /**
     * Constructs a new Event with a description
     * that occurs a certain date.
     * @param description The specified description.
     * @param time The specified date.
     */
    public Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Event(boolean isDone, String description, String date) {
        this(description, date);
        this.isDone = isDone;
    }

    public boolean isSameTask(Task toCheck) {
        if (toCheck instanceof Event) {
            return description.equals(toCheck.description)
                    && date.equals(((Event) toCheck).date);
        }
        return false;
    }

    /**
     * Converts an event to the format to be saved to a file.
     * @return The event in save format.
     */
    @Override
    public String encode() {
        assert description != null;
        assert date != null;
        String status = isDone ? "1" : "0";
        return String.format("%s/%s/%s/%s", ENCODED_TYPE, status, description, date);
    }

    /**
     * Converts an event to the format to be displayed to the user by the Ui.
     * @return The event in display format.
     */
    @Override
    public String toString() {
        assert description != null;
        assert date != null;
        return String.format("[E][%s] %s (at: %s)",
                getStatusIcon(),
                description,
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
