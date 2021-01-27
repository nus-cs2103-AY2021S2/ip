public class Event extends Task {
    String eventTime;

    public Event(String taskName, String eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }

    @Override
    public String generateDataString() {
        return "event" + taskName + eventTime + (done ? "done" : "notDone");
    }
}
