/**
 * Event class that is
 */
public class Event extends Task {

    private String timeslot;

    /**
     * Overloaded Constructor method
     * @param description String to describe the task.
     * @param timeslot String to describe the timeslot.
     */
    public Event(String description, String timeslot) {
        super(description);
        this.timeslot = timeslot;
    }

    /**
     * Overloaded constructor method
     * @param description
     * @param timeslot
     * @param isDone
     */
    public Event(String description, String timeslot, boolean isDone){
        super(description, isDone);
        this.timeslot = timeslot;
    }

    /**
     * Getter method for Event time.
     * @return
     */
    public String getTimeslot() {
        return this.timeslot;
    }

    /**
     * Method to change event timeslot
     * @param timeslot
     * @return
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