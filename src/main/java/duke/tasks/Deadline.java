package duke.tasks;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.DatetimeParser;

import java.time.LocalDateTime;

/**
 * Deadline class.
 *
 * A time-based class that tracks a description and a deadline.
 *
 * @see duke.tasks.Event
 */
public class Deadline extends DateTask {

    /**
     * Constructor for a Deadline.
     *
     * @param description Description of Deadline.
     * @param dt LocalDateTime of Deadline.
     */
    public Deadline(String description, LocalDateTime dt) {
        this(description, dt, false);
    }

    /**
     * Constructor for a Deadline.
     *
     * @param description Description of Deadline.
     * @param dt LocalDateTime of deadline.
     * @param isDone Whether task is completed.
     */
    public Deadline(String description, LocalDateTime dt, boolean isDone)  {
        super(description, isDone);
        this.datetime = dt;
    }

    /**
     * Returns new Deadline by parsing user string input.
     *
     * Input validation for date present. Dates should be provided as an argument
     * to the '/by' flag, following the description, e.g.
     * {@code deadline <description> /by <datetime>}.
     * Datetime formats are specified in {@link DatetimeParser }.
     *
     * @param s User input.
     * @return Deadline.
     * @throws DukeExceptionIllegalArgument When description is empty, datetime is empty,
     *                                      or datetime is invalid.
     */
    public static Deadline parse(String s) throws DukeExceptionIllegalArgument {
        if (s.equals("")) {
            throw new DukeExceptionIllegalArgument("The description of a deadline cannot be empty.");
        }

        String[] tokens = s.split(" /by ");
        if (tokens[0].equals("")) {
            throw new DukeExceptionIllegalArgument("The description of a deadline cannot be empty.");
        }
        if (tokens.length == 1 || tokens[1].equals("")) {
            throw new DukeExceptionIllegalArgument(
                    "An deadline must have both description and time,\ndelimited by '/by'.");
        }

        LocalDateTime dt = DatetimeParser.parseDate(tokens[1].strip());
        return new Deadline(tokens[0], dt);
    }

    /**
     * For pretty printing on stdout.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DatetimeParser.formatDate(datetime) + ")";
    }

    /**
     * For minified printing into file.
     *
     * @return String representation of Deadline.
     */
    public String toFileString() {
        return "D\t" + ((isDone) ? 1 : 0) + "\t" + description + "\t" + DatetimeParser.formatDateISO(datetime);
    }
}
