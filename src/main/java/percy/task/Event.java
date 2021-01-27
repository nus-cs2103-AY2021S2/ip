package percy.task;

public class Event extends Task {
    public static final String PREFIX = "E";
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String formatToFile() {
        return PREFIX + " | " + super.formatToFile() + " | "  + this.at;
    }
}
