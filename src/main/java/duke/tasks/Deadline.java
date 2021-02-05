package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a type of Task which has a description and date.
 * It also maintains a state of isDone.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns string representation of Deadline when saving locally.
     *
     * @return String of Deadline when saving.
     */
    public String saveString() {
        return isDone ? "D --- 1 --- " + description + " --- " + by : "D --- 0 --- " + description + " --- " + by;
    }

    /**
     * Converts user input for date in yyyy-mm-dd format into MMM d yyyy format.
     *
     * @param input A string representation of a date in the format yyyy-mm-dd.
     * @return String representation of date in MMM d yyyy format.
     */
    public static String convertToDate(String input) {
        try {
            LocalDate date = LocalDate.parse(input);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeException e) {
            return input;
        }
    }

    /**
     * Returns string representation of Deadline.
     *
     * @return String of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Deadline.convertToDate(by) + ")";
    }
}
