public class Event extends Task {
    private String time;

    public Event(String taskDescription, String time) {
        super(taskDescription);
        this.time = time;
    }

    @Override
    public String toString() {
        return "EVENT" + Task.TASK_DELIMITER + "[" + (done ? "X" : " ") + "]" + Task.TASK_DELIMITER + taskDescription + Task.TASK_DELIMITER + "(" + time + ")";
    }
}
