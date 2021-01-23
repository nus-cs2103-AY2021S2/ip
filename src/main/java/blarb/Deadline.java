package blarb;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * {@code Deadline} is a Task that has to be done before a specific date/time.
 *
 * @see Task
 */
class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructs a new uncompleted {@code Deadline}.
     *
     * @param description The name of the task.
     * @throws DateTimeParseException User inputs date in wrong style.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Converts {@code Deadline} into string format to be stored in file.
     *
     * @return String format to be stored in file.
     */
    public String encode() {
        return String.format("D / %s / %s", super.encode(), by);
    }

    /**
     * String representation of the Deadline.
     *
     * @return Deadline in check list form.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy GG")) + ")";
    }
}
