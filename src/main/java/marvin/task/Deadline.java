package marvin.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    public static final String ENCODED_TYPE = "D";
    protected LocalDateTime dateTime;

    /**
     * Constructor takes in the description of a deadline
     * and the date and time it is due by.
     * @param description The description of the deadline.
     * @param dateTime The date and time the deadline is due by.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Constructor takes in whether a deadline is done,
     * its description, and the date and time it is due by.
     * @param isDone True if the deadline is done, false otherwise.
     * @param description The description of the deadline.
     * @param dateTime The date and time the deadline is due by.
     */
    public Deadline(boolean isDone, String description, String dateTime) {
        this(description, dateTime);
        this.isDone = isDone;
    }

    /**
     * Checks if a task is the same as the task to check with.
     * @param toCheck The task to check with
     * @return True if the tasks are the same, false otherwise.
     */
    @Override
    public boolean isSameTask(Task toCheck) {
        if (toCheck instanceof Deadline) {
            return description.equals(toCheck.description)
                    && dateTime.equals(((Deadline) toCheck).dateTime);
        }
        return false;
    }

    /**
     * Encodes a deadline into a decodable string representation of the deadline.
     * @return The encoded string representation of the deadline.
     */
    @Override
    public String encode() {
        assert description != null;
        assert dateTime != null;
        String status = isDone ? "1" : "0";
        return String.format("%s/%s/%s/%s",
                ENCODED_TYPE,
                status,
                description,
                dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd Hmm")));
    }

    /**
     * Converts a deadline to the format to be displayed to the user by the Ui.
     * @return The deadline in display format.
     */
    @Override
    public String toString() {
        assert description != null;
        assert dateTime != null;
        return String.format("[D][%s] %s (by: %s)",
                getStatusIcon(),
                description,
                dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")));
    }
}
