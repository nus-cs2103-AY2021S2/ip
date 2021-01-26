package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private static DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate by;

    public Deadline(String taskName, boolean isCompleted, String by) {
        super(taskName, isCompleted);
        this.by = LocalDate.parse(by);
    }
    public String toStorageString() {
        return "D || " + (this.isCompleted ? "1" : "0") + " || "
                + this.taskName + " || " + this.by.format(OUTPUT_FORMATTER);

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(OUTPUT_FORMATTER) + ")";
    }
}

