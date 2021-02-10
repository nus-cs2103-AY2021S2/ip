/**
 * Event class that specifies an event happening at a certain time.
 */
public class Event extends Task {

    private String timeslot;

    /**
     * Constructs an event.
     * @param description task description
     * @param timeslot task timeslot
     */
    public Event(String description, String timeslot) {
        super(description);
        this.timeslot = timeslot;
    }

    /**
     * Constructs an event.
     * @param description task description
     * @param timeslot task timeslot
     * @param isDone task status
     */
    public Event(String description, String timeslot, boolean isDone) {
        super(description, isDone);
        this.timeslot = timeslot;
    }

    public String getEventTime() {
        return this.timeslot;
    }

    /**
     * Changes event timeslot
     * @param timeslot task timeslot
     * @return new timeslot
     */
    public String setEventTime(String timeslot) {
        this.timeslot = timeslot;
        return this.timeslot;
    }

    /**
     * Overrides Task's toString method.
     * @return String output for the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + timeslot + ")";
    }
}
