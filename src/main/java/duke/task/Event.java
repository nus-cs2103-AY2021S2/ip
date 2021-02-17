package duke.task;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
    private static final String TASK_TYPE = "EVENT";
    private LocalDateTime eventTime;

    /**
     * Constructor for Event
     * @param description
     * @param eventTime
     */
    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Constructor for Event with isDone
     * @param description
     * @param eventTime
     * @param isDone
     */
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
        String month = eventTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.US);
        return String.format("[%s] [%s] %s (at: %s %s %s, %02d:%02d)",
                this.getTaskType(), this.getStatusIcon(),
                this.getTaskDescription(), month,
                eventTime.getDayOfMonth(), eventTime.getYear(),
                eventTime.getHour(), eventTime.getMinute());
    }
}
