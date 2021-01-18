public class Event extends Task{
    private String timeOfEvent;

    public Event(String taskName, String timeOfEvent) {
        super(taskName);
        this.timeOfEvent = timeOfEvent;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", done ? "X" : " ", taskName, this.timeOfEvent);
    }
}
