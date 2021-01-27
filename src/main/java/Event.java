public class Event extends Task {

    private String timeslot;

    /**
     * Constructor method
     * @param description String to describe the task.
     * @param timeslot String to describe the timeslot.
     */
    public Event(String description, String timeslot) {
        super(description);
        this.timeslot = timeslot;
    }

    public Event(String description, String timeslot, boolean isDone) {
        super(description, isDone);
        this.timeslot = timeslot;
    }

    public String getTimeslot() {
        return this.timeslot;
    }

    public String changeTimeslot(String timeslot) {
        this.timeslot = timeslot;
        return this.timeslot;
    }

    /**
     * Overrides Task's toString method.
     * @return String output for the event.
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + timeslot + ")";
    }
}