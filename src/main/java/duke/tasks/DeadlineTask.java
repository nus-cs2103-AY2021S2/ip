package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Represents a DeadlineTask.
 */
public class DeadlineTask extends Task {
    private String type;
    private LocalDate deadline;

    /**
     *  DeadlineTask constructor.
     *
     *  @param taskName represents the name of the deadline task.
     *  @param deadline represents the time of the task that has to be done.
     */
    public DeadlineTask(String taskName, LocalDate deadline) {
        super(taskName, "[D]");
        this.deadline = deadline;
        this.type = "[D]";
    }

    public String getType() {
        return this.type;
    }

    /**
     *  Formats the task to file output format in the data file.
     *
     *  @return String that is in the correct format.
     */
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
