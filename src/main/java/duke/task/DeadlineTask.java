package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private static final String SEPARATOR = "|";
    private LocalDateTime date;

    public DeadlineTask(String taskName, LocalDateTime date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh.mm a")) + ")";
    }

    public String getSavingString() {
        return "DEADLINE" + super.getSavingString() + SEPARATOR
                + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + "\n";
    }
}
