public class Event extends Task {
    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getSaveString() {
        if (this.isDone()) {
            return String.format("event [isDone] %s /on %s\n", description, time);
        } else {
            return String.format("event %s /on %s\n", description, time);
        }
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (on: %s)", getStatus(), description, time);
    }
}