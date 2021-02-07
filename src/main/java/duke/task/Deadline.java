package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class is a type of Task and represents the abstraction
 * of a deadline which is due by a certain date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime time;

    /**
     * Constructs a new Deadline with a description
     * that is due by a certain date and time.
     * @param description The specified description.
     * @param time The specified date and time.
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd Hmm"));
    }

    /**
     * Converts a deadline to the format to be saved to a file.
     * @return The deadline in save format.
     */
    @Override
    public String toSaveFormat() {
        String status = super.isDone ? "1" : "0";
        return String.format("D|%s|%s\n", status, super.description, this.time);
    }

    /**
     * Converts a deadline to the format to be displayed to the user by the Ui.
     * @return The deadline in display format.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.getStatusIcon(),
                this.description,
                this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")));
    }
}
