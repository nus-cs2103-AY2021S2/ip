public class Event extends Task {

    protected String datetime;
    /**
     * Constructor method.
     * @param description User input description of Event
     * @param datetime Date and time of the Event
     */
    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }
    /**
     * Method to get the datetime of the Event
     * @return a String object of the datetime of the Event
     */
    public String getDatetime() {
        assert this.datetime.length() > 0;
        return this.datetime;
    }

    /**
     * Method to return a the Event object in the specified format
     * @return Formatted String of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + datetime + ")";
    }
}