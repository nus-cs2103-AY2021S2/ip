package duke;

import java.time.LocalDate;

public class Deadline extends Task{
    private static String taskType = "Deadline";
    private LocalDate endTime;

    public Deadline(String taskDescription, LocalDate endTime) {
        super(taskDescription);
        this.endTime = endTime;
    }

    public Deadline(String taskDescription, LocalDate endTime, boolean isDone) {
        super(taskDescription, isDone);
        this.endTime = endTime;
    }

    public String getTaskType() {

        return taskType;
    }

    public LocalDate getEndTime() {
        return endTime;
    }
}
