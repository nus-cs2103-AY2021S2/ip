import java.time.LocalDateTime;

public class Deadline extends TimedTask {

    /**
     * Initializes a Deadline Task with the specified description and dateTime.
     *
     * @param description Description of the Deadline Task.
     * @param dateTime Date and Time the Deadline Task should be done by.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description, dateTime, "D");
    }

    public Deadline(String description, LocalDateTime timeCreated, LocalDateTime dateTime) {
        super(description, timeCreated, dateTime, "D");
    }

    public String toString() {
        return super.toString() + " (by: " + dateTime.format(DATE_TIME_FORMAT) + ")";
    }
}
