package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class which creates a deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime date;

    /**
     * Creates Deadline task which keeps track of task details and deadlines.
     *
     * @param description description of the task to be saved
     * @param by deadline of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.date = by;
    }

    @Override
    public String toString() {
        String toPrint = "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
        assert !toPrint.isEmpty() : "Something should be printed.";
        return toPrint;
    }

    /**
     * Returns details of the deadline task.
     *
     * @return details of the task
     */
    @Override
    public String getTaskDetails() {
        return super.toString();
    }
}
