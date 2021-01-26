package duke;

public class Deadline extends Task{
    private static String taskType = "DEADLINE";
    private String endTime;

    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    public Deadline(String description, String endTime, boolean isDone) {
        super(description, isDone);
        this.endTime = endTime;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getEndTime() {
        return endTime;
    }
}
