package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    private static final String TASK_TYPE = "EVENT";
    private LocalDateTime eventTime;

    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public Event(String description, LocalDateTime eventTime, boolean isDone) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s (at: %s %s %s %02d:%02d)",
                this.getTaskType(), this.getStatusIcon(),
                this.getTaskDescription(), eventTime.getMonth(),
                eventTime.getDayOfMonth(), eventTime.getYear(),
                eventTime.getHour(), eventTime.getMinute());
    }
}
