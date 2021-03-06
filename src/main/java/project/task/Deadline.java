package project.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the application.
 * This extends the {@code Task} class.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Creates an instance of {@code Deadline}.
     *
     * @param description The deadline description.
     * @param deadline The date and time of the deadline.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);

        this.deadline = deadline;
        this.hasSchedule = true;
    }

    /**
     * Returns the date and time of occurrence.
     */
    @Override
    public LocalDateTime getOccurrence() {
        return deadline;
    }

    /**
     * Returns {@code String} representation of this {@code Deadline}.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"))
                + ")";
    }
}
