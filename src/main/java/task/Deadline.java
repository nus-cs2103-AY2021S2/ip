package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDate deadLine;

    public Deadline(String taskName, LocalDate deadLine) {
        super(taskName);
        this.deadLine = deadLine;
    }

    public Deadline(String taskName, boolean done, LocalDate deadLine) {
        super(taskName, done);
        this.deadLine = deadLine;
    }

    public String toString() {
        String formattedDate = this.deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    public String parseToCSVRow() {
        return "D," + super.isDone() + "," + super.getTaskName() + "," + this.deadLine;
    }
}
