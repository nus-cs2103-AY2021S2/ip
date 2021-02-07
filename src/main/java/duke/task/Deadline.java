package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Creates a Deadline task with the given description and deadline.
     * isDone is automatically set as False.
     *
     * @param description
     * @param deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Creates a Deadline task with the given description, isDone status, and deadline.
     *
     * @param description
     * @param isDone
     * @param deadline
     */
    public Deadline(String description, boolean isDone, String deadline) {
        this(description, isDone, LocalDate.parse(deadline));
    }

    private Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of Deadline
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Returns a string representation of Deadline to be saved in data file
     *
     * @return String
     */
    public String toSavedString() {
        return String.format("D | %d | %s | %s", super.isDone ? 1 : 0, super.description, deadline);
    }

    public Task clone() {
        return new Deadline(description, deadline.toString());
    }
}
