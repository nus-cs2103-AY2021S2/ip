package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    private static String taskType = "EVENT";
//    private String startTime;
//    private String endTime;
    private LocalDateTime eventTime;

    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public Event(String description, LocalDateTime eventTime, boolean isDone) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    public String getTaskType() {
        return taskType;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }
}
