package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private static final String SEPARATOR = "|";
    private LocalDateTime date;

    public EventTask(String taskName, LocalDateTime date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh.mm a")) + ")";
    }

    public String getSavingString() {
        return "EVENT" + super.getSavingString() + SEPARATOR + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + "\n";
    }
}
