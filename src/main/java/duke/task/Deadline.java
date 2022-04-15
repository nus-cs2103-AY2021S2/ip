package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates information and state of a Deadline.
 * For tasks that must be ccompletely dueDate a certain time.
 */
public class Deadline extends Task {
    /** Deadline of task. */
    protected LocalDate dueDate;

    /**
     * Initialises a new Deadline with text description and date.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        if (dueDate.contains("-")) {
            this.dueDate = LocalDate.parse(dueDate);
        } else {
            this.dueDate = LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(),
                super.toString(), this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
