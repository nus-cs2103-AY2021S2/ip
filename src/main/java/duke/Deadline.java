package duke;

public class Deadline extends Task{
    private static String taskType = "Deadline";
    private String endTime;

    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getEndTime() {
        return endTime;
    }
}
