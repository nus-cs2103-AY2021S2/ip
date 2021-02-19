package core.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Describes an Event.
 */
public class Event extends Task {
    private LocalDate atTime;

    /**
     * Creates a new Event task with a description. Must contain '/at' after which should be the event time.
     * @param desc - the description
     */
    public Event(String desc) {
        super(desc);

        var parts = desc.split("/at");
        this.taskDescription = parts[0].trim();
        this.atTime = LocalDate.parse(parts[1].trim());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + atTime.format(DateTimeFormatter.ISO_DATE_TIME) + ")";
    }
}
