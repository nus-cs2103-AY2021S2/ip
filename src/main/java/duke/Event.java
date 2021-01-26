package duke;

import java.time.LocalDate;

public class Event extends Task{
    private static String taskType = "Event";
//    private String startTime;
//    private String endTime;
    private LocalDate eventTime;

    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public String getTaskType() {
        return taskType;
    }

    public LocalDate getEventTime() {
        return eventTime;
    }
}
