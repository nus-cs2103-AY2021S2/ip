package duke.task;

public class Event extends Task {
    private String eventAt;

    /**
     * Default constructor for event
     * @param description String description of event
     * @param eventAt String description of where event is at
     */
    public Event(String description, String eventAt) {
        super(description);
        this.eventAt = eventAt;
    }

    /**
     * Alternative constructor for Event
     * @param description String description of event
     * @param eventAt String description of where event is at
     * @param doneInt integer to indicate if event is done
     */
    public Event(String description, String eventAt, int doneInt) {
        super(description, doneInt);
        this.eventAt = eventAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventAt + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E|" + super.toSaveFormat() + "|" + eventAt;
    }
}
