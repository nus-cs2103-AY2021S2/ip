package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime dateTime;

    public Event(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    public String getTaskType() {
        return "Event";
    }

    public String getDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }

    @Override
    public String toSave() {
        return "E / " + super.isDoneString + super.taskName + " / " + dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDateTime() + ")";
    }
}
