package duke;

import java.lang.Throwable;
public class Event extends Task {
    String at;

    Event() {

    }

    Event(String input) throws DukeIncompleteCommandException {
        input = input.substring(5).trim();

        if (input.equals("")) {
            throw new DukeIncompleteCommandException();
        }

        String[] inputs = input.split("/at");

        if (!input.contains("/at") || inputs.length < 2) {
            throw new DukeIncompleteCommandException("Oh no! Please enter an event date. :P");
        }

        this.task = inputs[0].trim();
        this.isDone = false;
        this.at = inputs[1].trim();
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
        event.at = lines[1].substring(0, lines[1].length() - 1);
        return event;
    }

    @Override
    public String toString() {
        return String.format("EVNT%s (at: %s)" ,
                super.toString(), at);
    }
}
