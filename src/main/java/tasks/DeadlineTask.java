package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private String type;
    private LocalDate deadline;

    public DeadlineTask(String taskName, LocalDate deadline) {
        super(taskName);
        this.type = "[D]";
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return this.type + super.toString() + " (by: " + this.deadline.format(dateFormat) + ")";
    }
}
