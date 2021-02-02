package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.utils.DateTime;

/**
 * Deadline task.
 */
public class Deadline extends Task {
    /**
     * Date the task is to be completed by.
     */
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Creates new instance of deadline.
     *
     * @param description Description of deadline.
     * @param dateTime Date and time the task is to be completed by.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        date = DateTime.parseDate(dateTime);
        time = DateTime.parseTime(dateTime);
    }

    @Override
    public String serialise() {
        String type = "DEADLINE";
        StringBuilder sb = new StringBuilder();
        sb.append(type).append('|')
            .append(isDone).append('|')
            .append(description).append('|')
            .append(DateTime.serialiseDate(date)).append('|')
            .append(DateTime.getTimeAsString(time));

        return sb.toString();
    }

    @Override
    public String toString() {
        String timeString = time == null ? "" : " " + time.toString();
        return String.format("[D] %s (by: %s%s)", super.toString(), DateTime.getDateAsString(date), timeString);
    }
}
