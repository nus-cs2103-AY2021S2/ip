package duke.task;

import duke.DukeHelper;

import java.time.LocalDate;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    private LocalDate by;

    public LocalDate getBy() {
        return by;
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public Deadline(int done, String description, String by) {
        super(done, description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), DukeHelper.formatDate(by));
    }

    @Override
    public String toStorageString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by);
    }
}
