package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline class which represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructs Deadline.
     *
     * @param isDone      Determines if task is completed.
     * @param description Task description.
     * @param date        Due date.
     */
    public Deadline(int isDone, String description, LocalDate date) {
        super('D', isDone, description);
        this.date = date;
    }

    /**
     * Creates String to be stored in the data file.
     *
     * @return String in the format to be stored in data file.
     */
    @Override
    public String getFileString() {
        return super.getFileString() + " // " + this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
