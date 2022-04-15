package duke.tasks;

import duke.parser.DuplicateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is task that is defined by a due date.
 */
public class Deadline extends Task implements DueDate {
    protected LocalDate time;

    /**
     * Constructs a deadline with its description, and due date.
     * The deadline has yet to be completed.
     *
     * @param description A String description of the deadline.
     * @param time Due date in yyyy-MM-dd format (e.g. 2021-03-12).
     */
    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructs a deadline with its description, due date, and its specified completion status.
     *
     * @param description A String description of the deadline.
     * @param isDone A boolean variable indicating if the deadline is completed.
     * @param time Due date in yyyy-MM-dd format (e.g. 2021-03-12).
     */
    public Deadline(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Marks a deadline as completed.
     *
     * @return A completed deadline with the same description.
     */
    public Deadline markAsDone() {
        return new Deadline(this.description, true, this.time);
    }

    /**
     * Returns the due date of the deadline.
     *
     * @return A String representation of the due date of the deadline.
     */
    @Override
    public String getDueDate() {
        return this.time.toString();
    }

    /**
     * Returns a String representation of the deadline.
     *
     * @return A String representation of the deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + this.time.format(formatter) + ")";
    }
}
