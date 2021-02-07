package duketask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Subclass of Task representing an event consisting
 * of additional member at to represent the datetime
 * of occurrence.
 */
public class Event extends Task {
    private LocalDateTime at;

    /**
     * Default constructor of Event which calls the
     * default super constructor:
     * state defaults to UNDONE.
     * createdDateTime defaults to current datetime.
     * @param description   the event description.
     * @param at            the event occurrence time.
     * @throws Task.EmptyDescriptionException
     */
    public Event (String description, LocalDateTime at)
        throws Task.EmptyDescriptionException {

        super(description);
        this.at = at;
    }

    public Event (String description, boolean isDone, LocalDateTime createdDateTime,
            LocalDateTime at) throws Task.EmptyDescriptionException {

        super(description, isDone, createdDateTime);
        this.at = at;
    }

    @Override
    public String getTaskInformation(DateTimeFormatter outputFormat) {
        return "[E]" + super.getTaskInformation(outputFormat) + " [ at: "
                + this.at.format(outputFormat) + " ]";
    }

    @Override
    public String toCommand (String delimiter, DateTimeFormatter parseFormat) {
        return "E" + delimiter + super.toCommand(delimiter, parseFormat) + delimiter
                + this.at.format(parseFormat);
    }
}
