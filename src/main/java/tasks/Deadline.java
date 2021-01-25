package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dateTime;

    public Deadline(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }

    @Override
    public String toSave() {
        return "D / " + super.isDoneString + super.taskName + " / " + dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateTime() + ")";
    }
}
