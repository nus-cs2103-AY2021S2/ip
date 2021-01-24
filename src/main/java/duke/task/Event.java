package duke.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime eventTime;

    public Event(String description, LocalDateTime eventTime) {
        super(description, "0");
        this.eventTime = eventTime;
    }

    /**
     * Constructor of the duke.Tasks.Event class.
     * @param description A brief description of the event.
     */

    public Event(String description, String isDone, LocalDateTime eventTime) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    public String getDateTime() {
        return eventTime.toLocalDate().toString()
                + " " + eventTime.toLocalTime().format(DateTimeFormatter.ofPattern("HHmm"));
    }

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
