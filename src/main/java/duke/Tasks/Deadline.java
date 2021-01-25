package duke.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /** Deadline of deadline */
    protected LocalDate by;

    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by);
        this.cat = 'D';
    }

    /** Returns deadline in the format yyy-MM-dd
     *
     * @return Deadline as a string
     * */
    public String getDeadline() {
        return this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /** Returns deadline in the format MMM d yyyy
     *
     * @return Deadline as a string */
    public String getFormattedDeadline() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.getFormattedDeadline() + ")";
    }

}

