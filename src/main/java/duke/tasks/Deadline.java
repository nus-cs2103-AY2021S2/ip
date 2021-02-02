package duke.tasks;

import java.time.LocalDateTime;

import duke.common.Utils;

/**
 * Represents a Deadline {@code Task}.
 */
public class Deadline extends Task {

    private final LocalDateTime by;

    /**
     * Constructor for Deadline {@code Task}, specifying the description and due date.
     *
     * @param description description of the deadline
     * @param by due date of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    /**
     * Constructor for Deadline {@code Task}, specifying the task's status, description and due date.
     *
     * @param done integer value to indicate the deadline's status
     * @param description description of the deadline
     * @param by due date of the deadline
     */
    public Deadline(int done, String description, String by) {
        super(done, description);
        this.by = LocalDateTime.parse(by);
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Utils.formatDate(by));
    }

    @Override
    public String toStorageString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by);
    }
}
