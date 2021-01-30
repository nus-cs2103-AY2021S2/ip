package duke.tasks;

import java.time.LocalDate;

import duke.Parser;

/**
 * Represents the subclass of Task. It contains the description of the task and the date of the
 * event.
 */
public class Event extends Task {
    private final LocalDate eventTime;

    /**
     * Construct a Event class that contains the description of the task and the date of the event.
     * @param description description of the Event.
     * @param eventTime date when the Event occurs.
     */
    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Return data representation of the Event to be saved into the save file.
     * @return data representation of the Event.
     */
    @Override
    public String data() {
        return String.format("E | %s | %s", super.data(), eventTime);
    }

    /**
     * Return string representation of the Event that will be shown to the user.
     * @return the event type, whether it is done, the event's description and the event's date.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), Parser.localDateToString(eventTime));
    }
}
