package core.task;

/**
 * Describes an Event.
 */
public class Event extends TimedTask {


    /**
     * Creates a new Event task with a description. Must contain '/at' after which should be the event time.
     * @param desc - the description
     */
    public Event(String desc) {
        super(desc, "/at");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + customFormat() + ")";
    }
}
