package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Represents a DeadlineTask.
 */
public class DeadlineTask extends Task {
    private String type;
    private LocalDate deadline;

    public DeadlineTask(String taskName, LocalDate deadline) {
        super(taskName);
        this.type = "[D]";
        this.deadline = deadline;
    }

    public String getType() {
        return this.type;
    }

    public String writeToFileFormat() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return String.format("%s|%s|%s|%s",
                "D",
                isDone == true ? "1" : "0",
                taskName,
                deadline.format(dateFormat));

    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return this.type + super.toString() + " (by: " + this.deadline.format(dateFormat) + ")";
    }
}
