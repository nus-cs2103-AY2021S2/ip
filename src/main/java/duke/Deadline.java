package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDate localBy;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.localBy = LocalDate.parse(by);
        this.type = 'D';
    }

    public String getBy() {
        return this.by;
    }

    public String getFormattedBy() {
        return localBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedBy() + ")";
    }
}
