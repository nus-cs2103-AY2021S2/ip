package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class which creates an event task.
 */
public class Event extends Task {
    private final LocalDateTime date;

    /**
     * Creates Event task which keeps track of task details and timings.
     *
     * @param description description of the task to be saved
     * @param at timing of the event
     * */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.date = at;
    }

    @Override
    public String toString() {
        String toPrint = "[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
        assert !toPrint.isEmpty() : "Something should be printed.";
        return toPrint;
    }

    /**
     * Returns details of the event task.
     *
     * @return details of the task
     */
    @Override
    public String getTaskDetails() {
        return super.toString();
    }
}
