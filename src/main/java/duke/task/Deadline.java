package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.parseDateTimeToString(by) + ")";
    }

    @Override
    public String getSaveFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + description +
                " | " + Parser.parseDateTimeToString(by);
    }
}
