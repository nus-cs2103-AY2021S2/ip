package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline object.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a String representing this object to be saved into a save data file.
     * @return String representation of this object, formatted for save data use.
     */
    public String getSaveString() {
        String datetimeString = deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (this.isDone()) {
            return String.format("[isDone] deadline %s /by %s\n", description, datetimeString);
        } else {
            return String.format("deadline %s /by %s\n", description, datetimeString);
        }
    }

    @Override
    public String toString() {
        String datetimeString = deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        assert datetimeString.length() > 0 : "Datetime string not successfully initialized";
        return String.format("[D][%s] %s (by: %s)", getStatus(), description, datetimeString);
    }
}