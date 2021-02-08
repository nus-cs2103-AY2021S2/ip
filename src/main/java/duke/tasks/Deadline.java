package duke.tasks;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.UserInputTokenSet;

/**
 * Deadline class.
 *
 * A time-based class that tracks a description and a deadline.
 *
 * @see duke.tasks.Event
 */
public final class Deadline extends DateTask {

    /**
     * Constructor for a Deadline.
     *
     * @param description Description of Deadline.
     * @param datetime LocalDateTime of deadline.
     * @param isDone Whether task is completed.
     */
    public Deadline(String description, String datetime, boolean isDone) throws DukeExceptionIllegalArgument {
        super(description, isDone);
        setDatetime(parseDatetime(datetime));
    }

    /**
     * Returns new Deadline by parsing user string input.
     *
     * Input validation for date present. Dates should be provided as an argument
     * to the '/by' flag, following the description, e.g.
     * {@code deadline <description> /by <datetime>}. '/done' flag can be optionally provided
     * to mark as completed.
     * Datetime formats are specified in {@link duke.parser.DatetimeParser }.
     *
     * @param tokenSet User input tokens
     * @return Deadline
     * @throws DukeExceptionIllegalArgument When description is empty, datetime is empty,
     *                                      or datetime is invalid.
     */
    public static Deadline parse(UserInputTokenSet tokenSet) throws DukeExceptionIllegalArgument {
        if (tokenSet.get("/text").isEmpty()) {
            throw new DukeExceptionIllegalArgument("The description of a deadline cannot be empty.");
        }
        if (!tokenSet.contains("by")) {
            throw new DukeExceptionIllegalArgument(
                    "A deadline must have both description and time,\ndelimited by '/by'.");
        }
        return new Deadline(
                tokenSet.get("/text"),
                tokenSet.get("by"),
                tokenSet.contains("done"));
    }

    /**
     * For pretty printing on stdout.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDatetimeString() + ")";
    }
}
