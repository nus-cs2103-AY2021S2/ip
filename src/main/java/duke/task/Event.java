package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for tasks that are events.
 */
public class Event extends Task {
    LocalDate eventTime;

    /**
     * Constructs an undone Event with a name and time.
     *
     * @param taskName Name of the Event.
     * @param eventTime Time of the Event.
     */
    public Event(String taskName, LocalDate eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    /**
     * Constructs an Event with a name, time and status.
     *
     * @param taskName Name of the Event.
     * @param done Status of the Event, done or not done.
     * @param eventTime Time of the Event.
     */
    public Event(String taskName, boolean done, LocalDate eventTime) {
        super(taskName, done);
        this.eventTime = eventTime;
    }

    /**
     * Returns a string representation of Event.
     *
     * @return A string representation of Event.
     */
    public String toString() {
        String formattedDate = this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }

    /**
     * Parse the java.duke.controller.task to comply with CSV format.
     *
     * @return A string that complies with CSV format.
     */
    public String parseToCSVRow() {
        return "E," + super.isDone() + "," + super.getTaskName() + "," + this.eventTime;
    }

    /**
     * Returns the deadline of the Event.
     *
     * @return Time of the Event.
     */
    public LocalDate getTaskTime() {
        return eventTime;
    }
}
