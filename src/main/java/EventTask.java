public class EventTask extends Task {
    private String event;

    public EventTask(String taskDescription, String event, int isDone) {
        super(taskDescription, "E", isDone);
        this.event = event;
    }

    @Override
    public String saveTask() {
        return String.format("%s /at %s", super.saveTask(), this.event);
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.event);
    }
}
