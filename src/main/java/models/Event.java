package models;

public class Event extends Todo {
    /** String denoting eventTime fo an Event object */
    protected String eventTime;

    /**
     * Constructor to allow setting of the eventTime in an Event object
     * 
     * @param message   String message that a Todo contains
     * @param eventTime String specific to events denoting when the event will be
     *                  occurring
     */
    public Event(String message, String eventTime) {
        super(message);
        this.eventTime = eventTime;
    }

    /**
     * Constructor to allow setting of the isDone attribute of an Event
     * 
     * @param message   String message that a Todo contains
     * @param isDone    boolean denoting if a Todo is done
     * @param eventTime String specific to events denoting when the event will be
     *                  occurring
     */
    public Event(String message, boolean isDone, String eventTime) {
        super(message, isDone);
        this.eventTime = eventTime;
    }

    /**
     * Getter method for event time of an Event object
     * 
     * @return String containing event time of the Event
     */
    public String getEventTime() {
        return this.eventTime;
    }

    @Override
    /**
     * Method overriden from Todo's getMessage method to return event type and
     * eventTime
     * 
     * @return String to be rendered to give information on the Event
     */
    public String getMessage() {
        return String.format("[E][%s] %s (at:%s)", this.getIsDoneIcon(), this.message, this.eventTime);
    }

    @Override
    /**
     * Method overridden the super class' to return a new Event that is marked as
     * done instead of a new Todo
     * 
     * @return Event that is marked as done
     */
    public Event markAsDone() {
        return new Event(this.message, true, this.eventTime);
    }
}
