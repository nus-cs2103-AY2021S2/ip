package duke;

import java.time.LocalDate;

public class Parser {
    private final Ui ui = new Ui();

    public Command parseCommand(String input) throws DukeException {
        String[] inputBreakdown = input.split(" ", 2);
        try {
            return Command.valueOf(inputBreakdown[0].toUpperCase());
        } catch (IllegalArgumentException error) {
            throw new DukeException();
        }
    }

    public int parseDoneCommand(String input) throws DukeException {
        String[] inputBreakdown = input.split(" ");
        if(inputBreakdown.length != 2) {
            throw new DukeException();
        }
        return Integer.parseInt(inputBreakdown[1]) - 1;
    }

    public int parseDeleteCommand(String input) throws DukeException {
        String[] inputBreakdown = input.split(" ");
        if(inputBreakdown.length != 2) {
            throw new DukeException();
        }
        return Integer.parseInt(inputBreakdown[1]) - 1;
    }

    public Todo parseTodoCommand(String input) throws DukeException {
        checkEmptyInput(input);
        return new Todo(input.substring(5));
    }

    public Deadline parseDeadlineCommand(String input) throws DukeException {
        checkEmptyInput(input);
        String[] args = input.substring(9).split(" /by ");
        return new Deadline(args[0], LocalDate.parse(args[1]));
    }

    public Event parseEventCommand(String input) throws DukeException {
        checkEmptyInput(input);
        String[] args = input.substring(6).split(" /at ");
        return new Event(args[0], args[1]);
    }

    public String parseFindCommand(String input) throws DukeException {
        checkEmptyInput(input);
        String keywords = input.substring(5);
        return keywords;
    }

    public boolean checkEmptyInput(String input) throws DukeException {
        String[] inputBreakdown = input.split(" ");
        if(inputBreakdown.length <= 1) {
            throw new DukeException();
        }
        return true;
    }
}
