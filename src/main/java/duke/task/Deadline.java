package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a new Deadline.
     * @param name
     * @param by
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns date in displayed format.
     * @return String
     */
    public String getDisplayBy() {
        return this.by.format(DateTimeFormatter.ofPattern(Task.DATE_DISPLAY_FORMAT));
    }

    /**
     * Returns date in saved format.
     * @return String
     */
    public String getSaveBy() {
        return this.by.format(DateTimeFormatter.ofPattern(Event.DATE_SAVE_FORMAT));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDisplayBy() + ")";
    }
}
