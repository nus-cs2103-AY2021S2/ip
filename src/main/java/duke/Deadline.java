package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * The Deadline class extends the Task class and includes a due date
 * to represent the time that the deadline must be completed.
 */
public class Deadline extends Task {
    static final String DATE_FORMAT = "MMM dd yyyy";
    private LocalDate dueDates;

    /**
     * Construct a deadline with the specified name and time.
     * @param task the task to be completed
     * @param dueDates the deadline time
     */
    public Deadline(String task, String dueDates) {
        super(task);
        this.dueDates = LocalDate.parse(dueDates);
    }

    /**
     * Overloaded constructor for deadline.
     * To be used in internal file manipulations
     * @param task the task to be completed
     * @param dueDates the deadline time
     */
    protected Deadline(String task, LocalDate dueDates) {
        super(task);
        this.dueDates = dueDates;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + dueDates.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ")";
    }
}
