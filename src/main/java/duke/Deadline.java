package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends AbstractTask {
    protected final LocalDate by;

    /**
     * Constructs a deadline task using the description and the deadline timing
     *
     * @throws DukeEmptyDescriptionException if the description is empty
     * @throws DateTimeParseException if the date cannot be parsed
     */
    public Deadline(String description, String by) throws DukeEmptyDescriptionException, DateTimeParseException {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /*
     * Return a string representation of the task
     */
    @Override
    public String toString() {
        String byFormatted = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return String.format("[D]%s (by: %s)", super.toString(), byFormatted);
    }
}
