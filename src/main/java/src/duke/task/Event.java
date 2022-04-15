package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event implementation of the super class Task. In addition to tracking event
 * time, this class provide methods to return strings relevant to user input.
 */
public class Event extends Task {
    private LocalDate eventTime;

    /**
     * Constructs an event that would happen as eventTime
     *
     * @param description name of the event
     * @param eventTime   time the event occurs
     */
    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * returns well formatted event status for the bot to print to the user
     *
     * @return status of event with relevant information
     */
    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + "(at: " + this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * returns current status of event
     *
     * @return current status of event
     */
    @Override
    public String currentStatus() {
        return "E" + super.currentStatus();
    }

    /**
     * Changes the task time to a new time
     */
    @Override
    public void changeEventTime(LocalDate newEventTime) {
        this.eventTime = newEventTime;
    }
}
