package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    LocalDate by;

    Deadline() {

    }
    static Deadline parseInput(String input) throws DukeIncompleteCommandException,
            DateTimeParseException {

        Deadline deadline = new Deadline();
        input = input.substring(8).trim();

        if (input.equals("")) {
            throw new DukeIncompleteCommandException();
        }

        String[] inputs = input.split("/by");

        if (!input.contains("/by") || inputs.length < 2) {
            throw new DukeIncompleteCommandException("Oh no! Please enter an due date. :P\n");
        }
        deadline.task = inputs[0].trim();
        deadline.isDone = false;
        deadline.by = LocalDate.parse(inputs[1].trim());

        return deadline;
    }

    static Deadline fileReader(String line) {
        Deadline deadline = new Deadline();
        if (line.charAt(5) == 'X') {
            deadline.isDone = true;
        } else {
            deadline.isDone = false;
        }
        String[] lines = line.substring(7).trim().split("by: ");
        deadline.task = lines[0].substring(0, lines[0].length() - 2).trim();
        String dateString = lines[1].substring(0, lines[1].length() - 1);
        deadline.by = LocalDate.parse(dateString.subSequence(0, dateString.length()));
        return deadline;
    }
    @Override
    public String toString() {
        return String.format("DDLN%s (by: %s)" ,
                super.toString(), by);
    }
    public String stringToSave() {
        return String.format("DDLN%s (at: %s)" , super.toString(), by);
    }
}
