public class Deadline extends Task {
    private String time;

    public Deadline(String taskDescription, String time) {
        super(taskDescription);
        this.time = time;
    }

    @Override
    public String toString() {
        return "DEADLINE" + Task.TASK_DELIMITER + "[" + (done ? "X" : " ") + "]" + Task.TASK_DELIMITER + taskDescription + Task.TASK_DELIMITER + "(" + time + ")";
    }
}
