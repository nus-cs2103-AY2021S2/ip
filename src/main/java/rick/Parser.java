package rick;

import rick.exceptions.*;

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

    public int parseDoneCommand(String input) throws InvalidDoneCommandException, NumberFormatException {
        String[] inputBreakdown = input.split(" ");
        if(inputBreakdown.length != 2) {
            throw new InvalidDoneCommandException();
        }
        return Integer.parseInt(inputBreakdown[1]) - 1;
    }

    public int parseDeleteCommand(String input) throws InvalidDeleteCommandException {
        String[] inputBreakdown = input.split(" ");
        if(inputBreakdown.length != 2) {
            throw new InvalidDeleteCommandException();
        }
        return Integer.parseInt(inputBreakdown[1]) - 1;
    }

    public Todo parseTodoCommand(String input) throws InvalidTodoCommandException {
        if(checkEmptyInput(input)) {
            throw new InvalidTodoCommandException();
        }
        return new Todo(input.substring(5));
    }

    public Deadline parseDeadlineCommand(String input) throws InvalidDeadlineCommandException, MissingFlagException {
        if(checkEmptyInput(input) || checkMissingFlag(input, "/by")) {
            throw new InvalidDeadlineCommandException();
        }
        String[] args = input.substring(9).split(" /by ");
        return new Deadline(args[0], LocalDate.parse(args[1]));
    }

    public Event parseEventCommand(String input) throws InvalidEventCommandException, MissingFlagException {
        if(checkEmptyInput(input) || checkMissingFlag(input, "/on")) {
            throw new InvalidEventCommandException();
        }
        String[] args = input.substring(6).split(" /on ");
        return new Event(args[0], LocalDate.parse(args[1]));
    }

    public String parseFindCommand(String input) throws InvalidFindCommandException {
        if(checkEmptyInput(input)) {
            throw new InvalidFindCommandException();
        }
        String keywords = input.substring(5).toUpperCase();
        return keywords;
    }

    public boolean checkEmptyInput(String input) {
        String[] inputBreakdown = input.split(" ");
        if(inputBreakdown.length <= 1) {
            return true;
        }
        return false;
    }

    public boolean checkMissingFlag(String input, String flag) {
        if(!input.contains(flag)) {
            return true;
        }
        return false;
    }
}
