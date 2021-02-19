package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class represents a Deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime cutOffTime;

    /**
     * Constructs a Deadline.
     * @param name The name of the Deadline task.
     * @param cutOff The cut off date of the Deadline task.
     */
    public Deadline(String name, LocalDateTime cutOff) {
        super(name);
        cutOffTime = cutOff;
    }

    /**
     * Returns a string in the given format.
     * @return A string in the given format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + cutOffTime.format(DateTimeFormatter.ofPattern("MMM dd yyy HH:mm")) + ")";
    }

    /**
     * Returns a string in the given format for storing in files.
     * @return A string in the given format for storing in files.
     */
    @Override
    public String toFileString() {
        return "D " + super.toFileString() + " | "
                + cutOffTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}

