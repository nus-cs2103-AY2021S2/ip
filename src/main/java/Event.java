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

    Event(String[] description) {
        super(description[0].substring(6), description[1]);
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
     * Overloaded constructor to set is done to a specified boolean value.
     * @param description Description of given task.
     * @param eventDate Date of task.
     * @param isDone Boolean whether task has been completed.
     */
    Event(String description, String eventDate, boolean isDone) {
        super(description, eventDate, isDone);
    }

    /**
     * Retrieving the given task type.
     * @return String giving the task type, in this case E.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * String method of event.
     * @return A formatted string of an event.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at:%s)", this.getTaskType(),
                this.getStatusIcon(), this.getDescription(), this.getEventDate());
    }

}
