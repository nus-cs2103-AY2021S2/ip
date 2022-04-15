/**
 * Event task class that specifies an event happening at a certain time.
 */
public class Event extends Task {

    private String timeSlot;

    /**
     * Construct an event object.
     *
     * @param description task description
     * @param timeslot task timeslot
     */
    public Event(String description, String timeslot) {
        super(description);
        this.timeSlot = timeslot;
    }

    /**
     * Constructs an event object.
     *
     * @param description task description
     * @param timeslot task timeslot
     * @param isDone task status
     */
    public Event(String description, String timeslot, boolean isDone) {
        super(description, isDone);
        this.timeSlot = timeslot;
    }

    /**
     * Gets the Event time.
     * @return timeslot
     */
    public String getEventTime() {
        return this.timeSlot;
    }

    /**
     * Changes event time
     *
     * @param timeslot task timeslot
     * @return new timeslot
     */
    public String setEventTime(String timeslot) {
        this.timeSlot = timeslot;
        return this.timeSlot;
    }

    /**
     * Overrides Task's toString method.
     * @return String output for the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + timeSlot + ")";
    }
}
