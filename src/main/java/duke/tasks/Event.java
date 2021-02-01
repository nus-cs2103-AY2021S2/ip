package duke.tasks;

public class Event extends Task {
    private static final String TYPE = "EVENT";
    protected String at;

    public Event(String description, String at) {
        super(description, TYPE);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}