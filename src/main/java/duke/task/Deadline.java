package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline extends from class Task and representing a task
 * that must be completed before a particular time/date.
 */
public class Deadline extends Task {
    private final String preposition;
    private final LocalDate date;

    /**
     * Returns a Deadline object representing the task.
     *
     * @param description Description of the deadline.
     * @param preposition Preposition used by the user (eg at, by).
     * @param date Latest date to finish the task.
     */
    public Deadline(String description, String preposition, LocalDate date) {
        super(description);
        this.preposition = preposition;
        this.date = date;
    }

    /**
     * Returns a string representation of the deadline to be stored in the
     * hard disk.
     *
     * @return A String representing the task (in the form of "deadline + description + |
     * + preposition + date".
     */
    @Override
    String toFileString() {
        return String.format("deadline %s | %s %s", description,
                preposition, date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Override
    public String toString() {
        String dateStr = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return String.format("[D][%s] %s (%s %s)", getStatusIcon(), description, preposition, dateStr);
    }
}
