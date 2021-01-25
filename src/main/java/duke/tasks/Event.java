package duke.tasks;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime dt) {
        this(description, dt, false);
    }
    public Event(String description, LocalDateTime dt, boolean isDone) {
        super(description, isDone);
        this.at = dt;
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
            throw new DukeExceptionIllegalArgument("An event must have both description and time.");
        }

        LocalDateTime dt = Parser.parseDate(tokens[1]);
        return new Event(tokens[0], dt);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Parser.formatDate(at) + ")";
    }

    public String toFileString() {
        return "E | " + ((isDone) ? 1 : 0) + " | " + description + " | " + Parser.formatDateISO(at);
    }
}
