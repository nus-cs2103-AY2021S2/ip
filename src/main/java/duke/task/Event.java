package duke.task;

import duke.utils.DateTime;

import java.time.LocalDate;

/**
 * Event task.
 */
public class Event extends Task {
    /** Date of event */
    protected LocalDate at;

    /**
     * Creates new instance of event.
     * @param description Description of event.
     * @param at Date of event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String serialise() {
        String type = "EVENT";
        StringBuilder sb = new StringBuilder();
        sb.append(type).append('|').append(isDone).append('|').append(description).append('|').append(DateTime.serialiseDate(at));

        return sb.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTime.getDate(at) + ")";
    }
}
