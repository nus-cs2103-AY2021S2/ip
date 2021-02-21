package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is to be completed by a deadline.
 */
public class Deadline extends Task {
    private final String by;
    private final LocalDate date;

    /**
     * Creates a new Deadline object with a description and deadline.
     * If deadline is not properly formatted in yyyy-mm-dd format, the date attribute will be null.
     *
     * @param description Description of the task.
     * @param by Deadline that the task is to be completed by (in yyyy-mm-dd format).
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        date = parseStringToDateTime(by);
    }

    private LocalDate parseStringToDateTime(String by) {
        if (by.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return LocalDate.parse(by);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        String deadline;
        if (date != null) {
            DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            deadline = date.format(myFormatter);
        } else {
            deadline = by;
        }
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String getTaskCommand() {
        return "deadline " + getDescription() + " /by " + by;
    }
}
