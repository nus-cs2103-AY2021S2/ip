public class Event extends Task {
    protected String at;

    public Event(int isDone, String description, String at) {
        super('E', isDone, description);
        this.at = at;
    }

    @Override
    public String getFileString() {
        return super.getFileString() + " // " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
