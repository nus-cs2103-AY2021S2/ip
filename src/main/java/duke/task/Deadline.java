package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private static String taskType = "DEADLINE";
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

        return taskType;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
