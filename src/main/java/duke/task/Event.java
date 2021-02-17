package duke.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Class representing an Event, a sub-class of Task.
 */
public class Event extends Task {
    private final LocalDateTime eventTime;

    /**
     * Constructor of the Event class.
     *
     * @param description A brief description of the Event.
     * @param eventTime Date and Time of the Event.
     */
    public Event(String description, LocalDateTime eventTime) {
        super(description, "0");
        this.eventTime = eventTime;
    }

    /**
     * Constructor of the Event class.
     *
     * @param description A brief description of the Event.
     * @param isDone "0" if the task is not done. "1" if the task is done.
     * @param eventTime Date and Time of the Event.
     */
    public Event(String description, String isDone, LocalDateTime eventTime) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    /**
     * Returns the Date and Time of the Event.
     *
     * @return A String representing the event date and time in the following format: "YYYY-MM-DD HHmm".
     */
    public String getDateTime() {
        return eventTime.toLocalDate().toString()
                + " " + eventTime.toLocalTime().format(DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Returns a String representation of the Event to be displayed to the user.
     * Shows the description, date and time of the Event, as well as whether it is done.
     *
     * @return A String representing the Event.
     */
    @Override
    public String toString() {
        int dayOfMonth = eventTime.getDayOfMonth();
        Month month = eventTime.getMonth();
        int year = eventTime.getYear();
        LocalTime time = eventTime.toLocalTime();
        String twelveHrTime = time.format(DateTimeFormatter.ofPattern("h:mm a"));
        return "[E]" + super.toString() + " (at: " + month.toString()
                + " " + dayOfMonth + " " + year + " " + twelveHrTime + ")";
    }
}
