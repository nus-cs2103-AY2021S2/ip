package main.java;

public class Event extends Task {
    protected String eventAt;
    public Event(String description, String eventAt) {
        super(description);
        this.eventAt = eventAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventAt + ")";
    }
}
