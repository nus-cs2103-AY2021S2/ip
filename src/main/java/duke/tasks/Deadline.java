package duke.tasks;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.Parser;

import java.time.LocalDateTime;

public class Deadline extends DateTask {

    public Deadline(String description, LocalDateTime dt) {
        this(description, dt, false);
    }
    public Deadline(String description, LocalDateTime dt, boolean isDone)  {
        super(description, isDone);
        this.datetime = dt;
    }

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

        LocalDateTime dt = Parser.parseDate(tokens[1].strip());
        return new Deadline(tokens[0], dt);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.formatDate(datetime) + ")";
    }

    public String toFileString() {
        return "D | " + ((isDone) ? 1 : 0) + " | " + description + " | " + Parser.formatDateISO(datetime);
    }
}
