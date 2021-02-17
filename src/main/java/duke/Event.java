package duke;

public class Event extends Task {
    public static final String SHORT_HAND = "E";
    protected final String at;

    /**
     * Construsts an Event task
     * @param taskDescription descripion of the event
     * @param at date/time of the event
     */
    public Event (String taskDescription, String at) {
        super(taskDescription);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[" + SHORT_HAND + "]" + super.toString() + " (at: " + this.at + ")";
    }
}
