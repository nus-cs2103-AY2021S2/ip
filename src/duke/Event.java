package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    LocalDate at;

    Event() {

    }

    static Event parseInput(String input) throws DukeIncompleteCommandException,
            DateTimeParseException {

        Event event = new Event();
        input = input.substring(5).trim();

        if (input.equals("")) {
            throw new DukeIncompleteCommandException();
        }

        String[] inputs = input.split("/at");

        if (!input.contains("/at") || inputs.length < 2) {
            throw new DukeIncompleteCommandException("Oh no! Please enter an event date. :P\n");
        }

        event.task = inputs[0].trim();
        event.isDone = false;
        event.at = LocalDate.parse(inputs[1].trim());
        return event;
    }
    static Event fileReader(String line) {
        Event event = new Event();
        if (line.charAt(5) == 'X') {
            event.isDone = true;
        } else {
            event.isDone = false;
        }
        String[] lines = line.substring(7).trim().split("at: ");
        event.task = lines[0].substring(0, lines[0].length() - 2).trim();
        String dateString = lines[1].substring(0, lines[1].length() - 1);
        event.at = LocalDate.parse(dateString.subSequence(0, dateString.length()));
        return event;
    }

    @Override
    public String toString() {
        return String.format("EVNT%s (at: %s)" , super.toString(),
                at.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
    public String stringToSave() {
        return String.format("EVNT%s (at: %s)" , super.toString(), at);
    }
}
