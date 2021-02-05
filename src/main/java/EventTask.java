import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task.
 */
public class EventTask extends Task {

    protected String eventDate;

    /**
     * Creates a new instance of <code>EventTask</code>.
     * @param description Description of event task.
     */
    public EventTask(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    /**
     * Returns date of event.
     *
     * @return Date of event.
     */
    public LocalDate getEventDateDate() {
        String[] eventDateArr = this.eventDate.split(" ");
        LocalDate eventDateDate = LocalDate.parse(eventDateArr[0]);
        return eventDateDate;
    }

    /**
     * Returns time of event.
     *
     * @return Time of event.
     */
    public LocalTime getEventDateTime() {
        String[] eventDateArr = this.eventDate.split(" ");
        LocalTime eventDateTime = LocalTime.parse(eventDateArr[1]);
        return eventDateTime;
    }


    /**
     * Returns String representation of event task.
     * @return String representation of event task.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + this.description
                + " (at: " + this.getEventDateDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + this.getEventDateTime().format(DateTimeFormatter.ofPattern("h:mma")) + ")";
    }
}