package tasks;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = "Event";
    }

    public Event(String description, Boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
        this.taskType = "Event";
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
