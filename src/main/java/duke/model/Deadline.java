package duke.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline class denotes a deadline.
 */
public class Deadline extends Task {
    private final LocalDate dateTime;

    /**
     * Construct a deadline.
     * @param isCompleted   Checked if the task is completed.
     * @param taskName     The task name.
     * @param dateTime     The date and time of the deadline.
     */
    public Deadline(boolean isCompleted, String taskName, LocalDate dateTime) {
        super('D', isCompleted, taskName);
        this.dateTime = dateTime;
    }

    /**
     * Generate a string to store in a file.
     * @return   A string that will be store in a file.
     */
    @Override
    public String generateFileFormatString() {
        return super.generateFileFormatString() + " // "
                + this.dateTime;
    }

    /**
     * Check if the given date equals to the task date time.
     * @param date   A local date.
     * @return       True if the given date equals to the task date time, otherwise false.
     */
    @Override
    public boolean checkEqualDate(LocalDate date) {
        return dateTime.isEqual(date);
    }

    /**
     * A string representation of a deadline.
     * @return  A string representing a deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
