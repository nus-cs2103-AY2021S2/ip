package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  Represents a EventTask.
 */
public class EventTask extends Task{
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EventTask(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskName);
        this.type = "[E]";
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getType() {
        return this.type;
    }

    public String writeToFileFormat() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return String.format("%s|%s|%s|%s|%s",
                "E",
                isDone == true ? "1" : "0",
                taskName,
                startTime.format(timeFormat),
                endTime.format(timeFormat));
    }

    @Override
    public String toString() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("hh:mm a");
        return this.type + super.toString() + " (at: " + this.startTime.format(timeFormat)
                + " to " + this.endTime.format(hourFormat) + ")";
    }
}
