package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Allows users to specify a deadline task. The user is able to specify the
 * description and by due date.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Initialises Deadline object.
     *
     * @param description the description of the task.
     * @param by the due date of the task, in the format YYYY-MM-DD
     */
    public Deadline(String description, String by) {
        super(description, "Deadline");
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the due date of the task.
     *
     * @return a String object of the due date.
     */
    public String getBy() {
        return by.toString();
    }

    /**
     * Returns a String of the Deadline task object in a standardised format.
     *
     * @return a String object of the Deadline task.
     */
    @Override
    public String toString() {
        String formatBy = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + "(by: " + formatBy + ")";
    }
}
