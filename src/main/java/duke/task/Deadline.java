package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    public String toSavedString() {
        return String.format("D | %d | %s | %s", super.isDone ? 1 : 0, super.description, deadline);
    }
}
