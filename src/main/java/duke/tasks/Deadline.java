package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task
 */
public class Deadline extends Task {
    private static DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate by;

    /**
     * Deadline constructor
     *
     * @param taskName Name of task
     * @param isCompleted Completion status of task
     * @param by Time of task deadline
     */
    public Deadline(String taskName, boolean isCompleted, String by) {
        super(taskName, isCompleted);
        this.by = LocalDate.parse(by);
    }

    public void editTime(String newTime) {
        this.by = LocalDate.parse(newTime);
    }
    public String getTime() {
        return this.by.format(OUTPUT_FORMATTER);
    }

    /**
     * Generates string to be stored in local disk
     * @return String with specific format
     */
    public String toStorageString() {
        return "D || " + (this.isCompleted ? "1" : "0") + " || " + this.taskName + " || "
                + this.by.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(OUTPUT_FORMATTER) + ")";
    }
}
