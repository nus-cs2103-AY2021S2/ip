package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event tasks are tasks that occur during a specified period of time.
 *
 * @author  Nicole Ang
 */
public class EventTask extends Task {
    protected LocalDate on;
    protected LocalTime from;
    protected LocalTime to;

    /**
     * Constructs a new EventTask object given a description and a time period.
     *
     * @param description   Task description.
     * @param on    Date event is occurring on.
     * @param from  Start time of event.
     * @param to    End time of event.
     */
    public EventTask(String description, LocalDate on, LocalTime from, LocalTime to) {
        super(description);
        this.on = on;
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task specifying its description and when it occurs.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + on + ", " + from + " - " + to + ")";
    }
}
