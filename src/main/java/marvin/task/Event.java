package marvin.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 */
public class Event extends Task {
    public static final String ENCODED_TYPE = "E";
    protected LocalDate date;

    /**
     * Constructor takes in the description of an
     * event task and the date it occurs at.
     * @param description The description of the event.
     * @param date The date the event occurs at.
     */
    public Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Constructor takes in whether an event is done,
     * its description, and the date it occurs at.
     * @param isDone True if the event is done, false otherwise.
     * @param description The description of the event.
     * @param date The date the event occurs at.
     */
    public Event(boolean isDone, String description, String date) {
        this(description, date);
        this.isDone = isDone;
    }

    /**
     * Checks if a task is the same as the task to check with.
     * @param toCheck The task to check with
     * @return True if the tasks are the same, false otherwise.
     */
    @Override
    public boolean isSameTask(Task toCheck) {
        if (toCheck instanceof Event) {
            return description.equals(toCheck.description)
                    && date.equals(((Event) toCheck).date);
        }
        return false;
    }

    /**
     * Encodes an event into a decodable string representation of the event.
     * @return The encoded string representation of the event.
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
