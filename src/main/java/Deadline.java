import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final String by;
    private boolean isSavedBefore;
    Deadline(String name, String by, boolean savedBefore) {
        super(name);
        this.by = by;
        this.isSavedBefore = savedBefore;
    }

    /**
     * Parse string to LocalDateTime
     * @param dateTimeString
     */
    public LocalDateTime parse(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Return the saved Format of Deadline task
     * @return saveFormat of deadline task
     */

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone() ? "1" : "0")
                + " | " + this.getTaskName() + " | " + (this.isSavedBefore ? this.by : this.parseDate());
    }

    public String parseDate() {
        return this.parse(this.by).format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    @Override
    public String toString() {
        return "[D]" + (this.done ? "[X] " : "[ ] ") + this.getTaskName() + " (by: "
                + (isSavedBefore ? this.by : parseDate()) + ")";
    }
}
