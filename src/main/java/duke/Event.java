package duke;

public class Event extends Task {
    protected final String at;
    public final String SHORT_HAND = "E";

    public Event (String taskDescription, String at) {
        super(taskDescription);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[" + SHORT_HAND +  "]" + super.toString() + " (at: " + this.at + ")";
    }
}
