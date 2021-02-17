package flamingo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates a Deadline task with a date.
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Creates a new Deadline.
     * @param description Description of the task.
     * @param date Date of the deadline.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String saveTask() {
        return "D" + super.saveTask() + " |" + date + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
