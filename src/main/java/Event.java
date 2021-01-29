public class Event extends Task {
    private String time;

    public Event(String taskDescription, String time) {
        super(taskDescription);
        this.time = time;
    }

    public Event(String taskDescription, boolean isDone, String time) {
        super(taskDescription, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "EVENT" + Task.TASK_DELIMITER + (isDone ? "done" : " ") + Task.TASK_DELIMITER + taskDescription + Task.TASK_DELIMITER + time;
    }
}
