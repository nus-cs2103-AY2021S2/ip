public class Deadline extends Task {
    private String time;

    public Deadline(String taskDescription, String time) {
        super(taskDescription);
        this.time = time;
    }

    public Deadline(String taskDescription, boolean isDone, String time) {
        super(taskDescription, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "DEADLINE" + Task.TASK_DELIMITER + (isDone ? "done" : " ") + Task.TASK_DELIMITER + taskDescription + Task.TASK_DELIMITER + time;
    }
}
