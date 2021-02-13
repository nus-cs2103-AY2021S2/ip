package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class is a type of Task and represents the abstraction
 * of a deadline which is due by a certain date and time.
 */
public class Deadline extends Task {
    public static final String ENCODED_TYPE = "D";
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

    public Deadline(boolean isDone, String description, String time) {
        this(description, time);
        this.isDone = isDone;
    }

    /**
     * Converts a deadline to the format to be saved to a file.
     * @return The deadline in save format.
     */
    @Override
    public String encode() {
        assert description != null;
        assert time != null;
        String status = isDone ? "1" : "0";
        return String.format("%s/%s/%s/%s",
                ENCODED_TYPE,
                status,
                description,
                time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd Hmm")));
    }

    /**
     * Converts a deadline to the format to be displayed to the user by the Ui.
     * @return The deadline in display format.
     */
    @Override
    public String toString() {
        assert description != null;
        assert time != null;
        return String.format("[D][%s] %s (by: %s)",
                getStatusIcon(),
                description,
                time.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")));
    }
}
