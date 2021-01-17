public class EventTask extends Task {
    private String event;

    public EventTask(String taskDescription, String event) {
        super(taskDescription);
        this.event = event;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.event);
    }
}
