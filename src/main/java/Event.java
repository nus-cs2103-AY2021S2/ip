/**
 * Event class that specifies an event happening at a certain time.
 */
public class Event extends Task {

    private String timeslot;

    /**
     * Overloaded Constructor method
     * @param description task description
     * @param timeslot task timeslot
     */
    public Event(String description, String timeslot) {
        super(description);
        this.timeslot = timeslot;
    }

    /**
     * Overloaded constructor method
     * @param description task description
     * @param timeslot task timeslot
     * @param isDone task status
     */
    public Event(String description, String timeslot, boolean isDone) {
        super(description, isDone);
        this.timeslot = timeslot;
    }

    /**
     * Getter method for Event time.
     * @return timeslot
     */
    public String getTimeslot() {
        return this.timeslot;
    }

    /**
     * Method to change event timeslot
     * @param timeslot task timeslot
     * @return new timeslot
     */
    public String changeTimeslot(String timeslot) {
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
