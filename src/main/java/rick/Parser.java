package rick;

import java.time.LocalDate;

public class Parser {
    public Command parseCommand(String input) throws RickException {
        String[] inputBreakdown = input.split(" ", 2);
        try {
            return Command.valueOf(inputBreakdown[0].toUpperCase());
        } catch (IllegalArgumentException error) {
            throw new RickException();
        }
    }

    public int parseDoneCommand(String input) throws RickException {
        String[] inputBreakdown = input.split(" ");
        if(inputBreakdown.length != 2) {
            throw new RickException();
        }
        return Integer.parseInt(inputBreakdown[1]) - 1;
    }

    public int parseDeleteCommand(String input) throws RickException {
        String[] inputBreakdown = input.split(" ");
        if(inputBreakdown.length != 2) {
            throw new RickException();
        }
        return Integer.parseInt(inputBreakdown[1]) - 1;
    }

    public Todo parseTodoCommand(String input) throws RickException {
        checkEmptyInput(input);
        return new Todo(input.substring(5));
    }

    public Deadline parseDeadlineCommand(String input) throws RickException {
        checkEmptyInput(input);
        String[] args = input.substring(9).split(" /by ");
        return new Deadline(args[0], LocalDate.parse(args[1]));
    }

    public Event parseEventCommand(String input) throws RickException {
        checkEmptyInput(input);
        String[] args = input.substring(6).split(" /on ");
        return new Event(args[0], LocalDate.parse(args[1]));
    }

    public String parseFindCommand(String input) throws RickException {
        checkEmptyInput(input);
        String keywords = input.substring(5);
        return keywords;
    }

    public boolean checkEmptyInput(String input) throws RickException {
        String[] inputBreakdown = input.split(" ");
        if(inputBreakdown.length <= 1) {
            throw new RickException();
        }
        return true;
    }
}
