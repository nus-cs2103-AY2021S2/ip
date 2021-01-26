package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Event extends Task{
    private static DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate at;

    public Event(String taskName, boolean isCompleted, String at) {
        super(taskName, isCompleted);
        this.at = LocalDate.parse(at);
    }

    public String toStorageString() {
        return "[E] || "+ (this.isCompleted ? "1" : "0")
                + " || " + this.taskName + " || " + this.at.format(OUTPUT_FORMATTER);

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(OUTPUT_FORMATTER) + ")";
    }
}
