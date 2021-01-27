public class Event extends Task {
    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    public Event(String description, String duration, boolean isDone) {
        super(description, true);
        this.duration = duration;
    }

    @Override
    public Event finishTask() {
        return new Event(description, duration, true);
    }

    @Override
    public String saveTask() {
        return String.format("E | %s | %s | %s\n", super.getStatusIcon(),
                description, duration);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration + ")";
    }
}