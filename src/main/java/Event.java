import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Representation of a Event task. Inherits from Task.
 */
public class Event extends Task {
    String eventTime;
    LocalDate localDate;
    LocalTime localTime;

    /**
     * Class constructor. Creates a not done Event object with name set to the specified taskName
     * and event time set to the specified eventTime. Converts and stores the eventTime as LocalDate and LocalTime.
     * @param taskName The specified name of the new Event object.
     * @param eventTime The specified event time of the new Event object.
     */
    public Event(String taskName, String eventTime) {
        super(taskName);
        this.eventTime = eventTime;

        String[] eventTimeSplit = eventTime.split(" ");

        localDate = LocalDate.parse(eventTimeSplit[0]);

        if (eventTimeSplit.length > 1) {
            int time = Integer.parseInt(eventTimeSplit[1]);
            localTime = LocalTime.of(time/100, time % 100);
        } else {
            localTime = LocalTime.MIDNIGHT;
            this.eventTime += " 0000";
        }
    }

    /**
     * A method to create a neatly formatted String that describes this Event.
     * @return Neatly formatted String representation of this Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + LocalDateTime.of(localDate, localTime).format(DateTimeFormatter.ofPattern("MMM d yyy h:m a"))
                + ")";
    }

    /**
     * Generates a formatted String for storage read and write purposes.
     * @return Formatted data String to be used by Storage.
     */
    @Override
    public String generateDataString() {
        return "event " + eventTime + (done ? " done " : " notDone ") + taskName;
    }
}
