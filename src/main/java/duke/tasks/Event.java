package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event task
 */
public class Event extends Task {
    private static DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate at;

    /**
     * Event constructor
     *
     * @param taskName Name of task
     * @param isCompleted Completion status of task
     * @param at Time of event
     */
    public Event(String taskName, boolean isCompleted, String at) {
        super(taskName, isCompleted);
        this.at = LocalDate.parse(at);
    }

    public void editTime(String newTime) {
        this.at = LocalDate.parse(newTime);
    }
    public String getTime() {
        return this.at.format(OUTPUT_FORMATTER);
    }

    /**
     * Generates string to be stored in local disk
     * @return String with specific format
     */
    public String toStorageString() {
        return "[E] || " + (this.isCompleted ? "1" : "0") + " || " + this.taskName + " || "
                + this.at.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(OUTPUT_FORMATTER) + ")";
    }
}
