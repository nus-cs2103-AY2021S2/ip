package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Allows users to specify an event task. The user is able to specify the
 * description and at due date.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Initialises Event object.
     *
     * @param description the description of the task.
     * @param at the date of the task, in the format YYYY-MM-DD
     */
    public Event(String description, String at) {
        super(description, "Event");
        this.at = LocalDate.parse(at);
    }

    /**
     * Returns the date of the task
     *
     * @return a String object of the date.
     */
    public String getAt() {
        return at.toString();
    }

    /**
     * Returns a String of the Event task object in a standardised format.
     *
     * @return a String object of the Event task.
     */
    @Override
    public String toString() {
        String formatAt = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E]" + super.toString() + "(at: " + formatAt + ")";
    }
}
