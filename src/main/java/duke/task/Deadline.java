package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private static final String TASK_TYPE = "DEADLINE";
    private LocalDateTime endTime;

    public Deadline(String taskDescription, LocalDateTime endTime) {
        super(taskDescription);
        this.endTime = endTime;
    }

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
        return String.format("[%s] [%s] %s (by: %s %s %s %02d:%02d)",
                this.getTaskType(), this.getStatusIcon(),
                this.getTaskDescription(), endTime.getMonth(),
                endTime.getDayOfMonth(), endTime.getYear(),
                endTime.getHour(), endTime.getMinute());
    }
}
