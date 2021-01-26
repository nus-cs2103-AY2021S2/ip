package main.java.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public String fileFormat() {
        return "D | " + (super.isDone ? "1 | " : "0 | ") + this.description + " | " +
                this.deadline.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public Deadline markAsDone() {
        return new Deadline(description, deadline, true);
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
