package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class is a type of Task and represents the
 * abstraction of an event which occurs at a certain date.
 */
public class Event extends Task {
    public static final String ENCODED_TYPE = "E";
    protected LocalDate time;

    /**
     * Constructs a new Event with a description
     * that occurs a certain date.
     * @param description The specified description.
     * @param time The specified date.
     */
    public Event(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Event(boolean isDone, String description, String time) {
        this(description, time);
        this.isDone = isDone;
    }

    public boolean isSameTask(Task toCheck) {
        if (toCheck instanceof Event) {
            return description.equals(toCheck.description)
                    && time.equals(((Event) toCheck).time);
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
        assert time != null;
        String status = isDone ? "1" : "0";
        return String.format("%s/%s/%s/%s", ENCODED_TYPE, status, description, time);
    }

    /**
     * Converts an event to the format to be displayed to the user by the Ui.
     * @return The event in display format.
     */
    @Override
    public String toString() {
        assert description != null;
        assert time != null;
        return String.format("[E][%s] %s (at: %s)",
                getStatusIcon(),
                description,
                time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
