package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Todo {
    private static final DateTimeFormatter inputDateFormat =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter outputDateFormat =
            DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH:mm");
    /** String denoting eventTime fo an Event object */
    protected String eventStringDateTime;
    protected LocalDateTime eventDateTime;

    /**
     * Constructor to allow setting of the eventTime in an Event object
     *
     * @param message String message that a Todo contains
     * @param eventStringDateTime String specific to events denoting when the event will be occurring
     */
    public Event(String message, String eventStringDateTime) {
        super(message);
        this.eventStringDateTime = eventStringDateTime;
        this.eventDateTime = parseStringToLocalDateTime(eventStringDateTime);
    }

    /**
     * Constructor to allow setting of the isDone attribute of an Event
     *
     * @param message String message that a Todo contains
     * @param isDone boolean denoting if a Todo is done
     * @param eventStringDateTime String specific to events denoting when the event will be occurring
     */
    public Event(String message, boolean isDone, String eventStringDateTime) {
        super(message, isDone);
        this.eventStringDateTime = eventStringDateTime;
        this.eventDateTime = parseStringToLocalDateTime(eventStringDateTime);
    }

    /**
     * Expects String in the format dd/MM/yyyy HHMM and returns LocalDateTime object
     *
     * @param eventDateTimeString String passed in with the format dd/MM/yyyy HHMM
     * @return LocalDateTime object from String eventTimeString passed in
     * @throws DateTimeParseException when the date time is in the wrong format
     */
    private LocalDateTime parseStringToLocalDateTime(String eventDateTimeString)
            throws DateTimeParseException {
        // @formatter:off
        return LocalDateTime.parse(eventDateTimeString.length() == 15
                ? eventStringDateTime
                : String.format("0%s", eventStringDateTime), inputDateFormat);
    }

    /**
     * Getter method for event time from LocalDateTime of an Event object
     *
     * @return String containing event time of the Event, parsed from LocalDateTime object
     */
    public String getEventTime() {
        return this.eventDateTime.format(inputDateFormat);
    }

    /**
     * Getter method for event time from LocalDateTime of an Event object
     *
     * @return String containing event time of the Event, parsed from LocalDateTime object in a
     *         prettier format
     */
    public String getPrettierEventTime() {
        return String.format("%s hrs", this.eventDateTime.format(outputDateFormat));
    }

    /**
     * Method overridden from Todo's getMessage method to return event type and eventTime
     *
     * @return String to be rendered to give information on the Event
     */
    @Override
    public String getMessage() {
        return String.format("[E][%s] %s (at: %s)", this.getIsDoneIcon(), this.message,
                this.getPrettierEventTime());
    }

    /**
     * Method overridden the super class' to return a new Event that is marked as done instead of a
     * new Todo
     *
     * @return Event that is marked as done
     */
    @Override
    public Event markAsDone() {
        return new Event(this.message, true, this.getEventTime());
    }
}
