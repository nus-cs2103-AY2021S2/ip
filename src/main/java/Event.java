public class Event extends Task {

    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    public Event(int done, String description, String by) {
        super(done, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStorageString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, by);
    }
}
