package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specified deadline.
 */
public class Deadline extends Task {
    private static final int DATE_LENGTH = 10;
    private static final String DATE_FORMAT = "d MMM yyyy";
    protected LocalDate date;
    protected String time;

    public Deadline(String task, String by) {
        super(task);
        this.date = LocalDate.parse(by.substring(0, DATE_LENGTH));
        this.time = by.substring(DATE_LENGTH);
    }

    public Deadline(boolean done, String task, String by) {
        super(task);
        this.isDone = done;
        this.date = LocalDate.parse(by.substring(0, DATE_LENGTH));
        this.time = by.substring(DATE_LENGTH);
    }

    /**
     * Returns a String representation of this Deadline for storage to a file.
     * @return String representation
     */
    public String toFileString() {
        return "D | " + this.isDone + " | " + this.task + " | " + this.date + this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + this.date.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + " "
                + this.time + ")";
    }
}
