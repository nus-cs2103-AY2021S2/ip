public class Event extends Task{
    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    public Event(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public Event markAsDone() {
        return new Event(this.description, true, this.by);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }
}
