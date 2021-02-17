package duke.tasks;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.UserInputTokenSet;


/**
 * Event class.
 *
 * A time-based class that tracks a description and an event time.
 *
 * @see duke.tasks.Deadline
 */
public final class Event extends DateTask {

    /**
     * Constructor for an Event.
     *
     * @param description Description of Event.
     * @param datetime LocalDateTime of Event.
     * @param isDone Whether task is completed.
     */
    public Event(String description, String datetime, boolean isDone) throws DukeExceptionIllegalArgument {
        super(description, isDone);
        setDatetime(parseDatetime(datetime));
    }

    /**
     * Returns new Event by parsing user input tokens.
     *
     * Input validation for date present. Dates should be provided as an argument
     * to the '/at' flag, following the description, e.g.
     * {@code event <description> /at <datetime>}. '/done' flag can be optionally provided
     * to mark as completed.
     * Datetime formats are specified in {@link duke.parser.DatetimeParser }.
     *
     * @param tokenSet User input tokens
     * @return Event
     * @throws DukeExceptionIllegalArgument When description is empty, datetime is empty,
     *                                      or datetime is invalid.
     */
    public static Event parse(UserInputTokenSet tokenSet) throws DukeExceptionIllegalArgument {
        if (tokenSet.get("/text").isEmpty()) {
            throw new DukeExceptionIllegalArgument("The description of an event cannot be empty.");
        }
        if (!tokenSet.contains("at")) {
            throw new DukeExceptionIllegalArgument(
                    "An event must have both description and time,\ndelimited by '/at'.");
        }
        return new Event(
                tokenSet.get("/text"),
                tokenSet.get("at"),
                tokenSet.contains("done"));
    }

    /**
     * For pretty printing on stdout.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDatetimeString() + ")";
    }
}
