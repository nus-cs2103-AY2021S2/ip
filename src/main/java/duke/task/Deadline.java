package duke.task;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    private static final String TASK_TYPE = "DEADLINE";
    private LocalDateTime endTime;

    /**
     * Constructor for Deadline
     * @param taskDescription
     * @param endTime
     */
    public Deadline(String taskDescription, LocalDateTime endTime) {
        super(taskDescription);
        this.endTime = endTime;
    }

    /**
     * Constructor for Deadline
     * @param taskDescription
     * @param endTime
     * @param isDone
     */
    public Deadline(String taskDescription, LocalDateTime endTime, boolean isDone) {
        super(taskDescription, isDone);
        this.endTime = endTime;
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        String month = endTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.US);
        return String.format("[%s] [%s] %s (by: %s %s %s, %02d:%02d)",
                this.getTaskType(), this.getStatusIcon(),
                this.getTaskDescription(), month,
                endTime.getDayOfMonth(), endTime.getYear(),
                endTime.getHour(), endTime.getMinute());
    }
}
