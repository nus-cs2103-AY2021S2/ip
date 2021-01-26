package core.task;

/**
 * Describes an Event.
 */
public class Event extends Task {
    private String atTime;

    /**
     * Creates a new Event task with a description. Must contain '/at' after which should be the event time.
     * @param desc - the description
     */
    public Event(String desc) {
        super(desc);

        var parts = desc.split("/at");
        this.taskDescription = parts[0].trim();
        this.atTime = parts[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + atTime + ")";
    }
}
