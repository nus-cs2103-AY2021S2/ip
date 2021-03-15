package project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a "deadline" task.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"))
                + ")";
    }
}
