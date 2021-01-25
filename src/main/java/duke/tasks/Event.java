package duke.tasks;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends DateTask {

    protected LocalDateTime datetime;

    public Event(String description, LocalDateTime dt) {
        this(description, dt, false);
    }
    public Event(String description, LocalDateTime dt, boolean isDone) {
        super(description, isDone);
        this.datetime = dt;
    }

    public static Event parse(String s) throws DukeExceptionIllegalArgument {
        if (s.equals("")) {
            throw new DukeExceptionIllegalArgument("The description of an event cannot be empty.");
        }

        String[] tokens = s.split(" /at ");
        if (tokens[0].equals("")) {
            throw new DukeExceptionIllegalArgument("The description of an event cannot be empty.");
        }
        if (tokens.length == 1 || tokens[1].equals("")) {
            throw new DukeExceptionIllegalArgument(
                    "An event must have both description and time,\ndelimited by '/at'.");
        }

        LocalDateTime dt = Parser.parseDate(tokens[1].strip());
        return new Event(tokens[0], dt);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Parser.formatDate(datetime) + ")";
    }

    public String toFileString() {
        return "E | " + ((isDone) ? 1 : 0) + " | " + description + " | " + Parser.formatDateISO(datetime);
    }
}
