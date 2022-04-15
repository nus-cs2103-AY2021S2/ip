public class Event extends Task {
    protected Date date;
    protected String duration;

    /**
     * Creates an Event object.
     *
     * @param description A description of the task.
     * @param date A date stating when the event is going to be.
     * @param duration A string containing the time period of the event.
     * @param type Type of task the object is.
     */
    public Event(String description, Date date, String duration, String type) {
        super(description, type);
        this.date = date;
        this.duration = duration;
    }


    /**
     * Converts the Event object to a String.
     *
     * @return String showing the details of the Event object.
     */
    @Override
    public String toString() {
        String result = "[E]" + super.toString() + " (at: " + date.toFormattedString() + " " + duration + ")";
        return priority != null
                ? result + " " + "[" + priority.toString() + "]"
                : result;
    }
}
