import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime eventTime;

    /**
     * Constructor of the Event class.
     * @param description A brief description of the event.
     */

    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
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
