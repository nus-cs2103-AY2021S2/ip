package duke;

import java.time.LocalDate;

public class Deadline extends Task{
    private static String taskType = "Deadline";
    private LocalDate endTime;

    public Deadline(String description, LocalDate endTime) {
        super(description);
        this.endTime = endTime;
    }

    public String getTaskType() {
        return taskType;
    }

    public LocalDate getEndTime() {
        return endTime;
    }
}
