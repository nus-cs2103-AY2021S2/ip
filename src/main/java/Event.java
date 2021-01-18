package main.java;

public class Event extends Task {
    private String timeRange;

    public Event(String description, String timeRange) {
        super(description);
        this.timeRange = timeRange;
    }

    public Event(String description, String timeRange, boolean isDone) {
        super(description, isDone);
        this.timeRange = timeRange;
    }

    @Override
    public Event markAsDone() {
        return new Event(description, timeRange, true);
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + timeRange + ")";
    }
}
