package task;

import java.time.LocalDate;

/**
 * Deadline tasks
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Make deadline
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
