package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Task that has a time representing the deadline.
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Creates a Deadline
     *
     * @param description the Task's name
     * @param deadline the Task's deadline
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Creates a Deadline.
     *
     * @param description the Task's name
     * @param deadline the Task's deadline
     * @param isDone status of Task's completion
     */
    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Formats a Deadline for storing in the file.
     *
     * @return a String representation of the Deadline
     */
    public String fileFormat() {
        return "D | " + (super.isDone ? "1 | " : "0 | ") + this.description + " | " +
                this.deadline.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Marks a Deadline as done.
     *
     * @return a new Deadline that is considered done
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(description, deadline, true);
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return obj instanceof Deadline && description.equals(((Deadline) obj).getDescription())
                    && deadline.equals(((Deadline) obj).getDeadline()) && isDone == ((Deadline) obj).isDone();
        }
    }
}
