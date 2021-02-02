package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.utils.DateTime;

/**
 * Event task.
 */
public class Event extends Task {
    /**
     * Date of event
     */
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Creates new instance of event.
     *
     * @param description Description of event.
     * @param dateTime Date and time of event.
     */
    public Event(String description, String dateTime) {
        super(description);
        date = DateTime.parseDate(dateTime);
        time = DateTime.parseTime(dateTime);
    }

    @Override
    public String serialise() {
        String type = "EVENT";
        StringBuilder sb = new StringBuilder();
        sb.append(type).append('|')
            .append(isDone).append('|')
            .append(description).append('|')
            .append(DateTime.serialiseDate(date)).append('|')
            .append(DateTime.getTimeAsString(time));;

        return sb.toString();
    }

    @Override
    public String toString() {
        String timeString = time == null ? "" : " " + time.toString();
        return String.format("[E] %s (at: %s%s)", super.toString(), DateTime.getDateAsString(date), timeString);
    }
}
