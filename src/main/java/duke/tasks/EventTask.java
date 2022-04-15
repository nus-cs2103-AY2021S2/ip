package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  Represents a EventTask.
 */
public class EventTask extends Task {
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     *  EventTask constructor.
     *
     *  @param taskName represents the name of the event task.
     *  @param startTime represents the starting time of the task.
     *  @param endTime represents the ending time of the task.
     */
    public EventTask(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskName, "[E]");
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = "[E]";
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
