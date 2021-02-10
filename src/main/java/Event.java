import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM" +
            "/yyyy HHmm");

    private LocalDateTime time;

    /**
     * Main Constructor.
     * @param description Description of given task.
     */
    Event(String description) {
        super(description);
    }

    /**
     * Overloaded constructor to account for deadline and events that have timestamps.
     * @param description Description of given task.
     * @param eventDate Timestamp of given task.
     */
    Event(String description, String eventDate) {
        super(description, eventDate);

        try {
            time = LocalDateTime.parse(eventDate, formatter);
        } catch (Exception e) {
            time = null;
        }
    }

    /**
     * Retrieving the given task type.
     * @return String giving the task type, in this case E.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s(at:%s)", this.getTaskType(),
                this.getStatusIcon(), this.getDescription(), this.getEventDate());
    }

}
