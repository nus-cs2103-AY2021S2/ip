package parser;

import java.time.DateTimeException;
import java.time.LocalDate;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import data.Deadline;
import data.Event;
import data.Todo;

public class Parser {
    /**
     * Parses the given input to get the correct command
     * @param input
     * @return Command
     * @throws ParserException
     */
    public Command parseCommand(String input) throws ParserException {
        String[] tokens = input.split(" ");
        switch (tokens[0]) {
        case AddTodoCommand.COMMAND_TEXT:
            return prepareAddTodo(input, tokens);
        case AddDeadlineCommand.COMMAND_TEXT:
            return prepareAddDeadline(input, tokens);
        case AddEventCommand.COMMAND_TEXT:
            return prepareAddEvent(input, tokens);
        case ListCommand.COMMAND_TEXT:
            return new ListCommand();
        case DoneCommand.COMMAND_TEXT:
            return prepareDone(input, tokens);
        case DeleteCommand.COMMAND_TEXT:
            return prepareDelete(input, tokens);
        case FindCommand.COMMAND_TEXT:
            return prepareFind(input, tokens);
        case ExitCommand.COMMAND_TEXT:
            return new ExitCommand();
        default:
            throw new ParserException("Unknown command.");
        }
    }

    private Command prepareAddTodo(String input, String[] tokens) throws ParserException {
        if (tokens.length < 2) {
            throw new ParserException("Todo requires a description.");
        }

        Todo todo = new Todo(input.substring(5));
        return new AddTodoCommand(todo);
    }

    private Command prepareAddDeadline(String input, String[] tokens) throws ParserException {
        int bySwitchIndex = input.indexOf("/by");
        if (bySwitchIndex == -1 || bySwitchIndex + 4 > input.length()) {
            throw new ParserException("Deadline requires '/by' to be specified.");
        }
        String description = input.substring(9, bySwitchIndex);
        if (description.trim().equals("")) {
            throw new ParserException("Deadline requires a description.");
        }
        String byStr = input.substring(bySwitchIndex + 4);
        if (byStr.trim().equals("")) {
            throw new ParserException("Deadline requires '/by' to be specified.");
        }

        LocalDate by;
        try {
            by = LocalDate.parse(byStr);
        } catch (DateTimeException dte) {
            throw new ParserException("Deadline /by needs to be in a valid format (e.g. yyyy-MM-dd)");
        }

        Deadline deadline = new Deadline(description, by);
        return new AddDeadlineCommand(deadline);
    }

    private Command prepareAddEvent(String input, String[] tokens) throws ParserException {
        int atSwitchIndex = input.indexOf("/at");
        if (atSwitchIndex == -1 || atSwitchIndex + 4 > input.length()) {
            throw new ParserException("Event requires '/at' to be specified.");
        }
        String description = input.substring(6, atSwitchIndex);
        if (description.trim().equals("")) {
            throw new ParserException("Event requires a description.");
        }
        String atStr = input.substring(atSwitchIndex + 4);
        if (atStr.trim().equals("")) {
            throw new ParserException("Event requires '/at' to be specified.");
        }

        LocalDate at;
        try {
            at = LocalDate.parse(atStr);
        } catch (DateTimeException dte) {
            throw new ParserException("Event /at needs to be in a valid format (e.g. yyyy-MM-dd)");
        }

        Event event = new Event(description, at);
        return new AddEventCommand(event);
    }

    private Command prepareDone(String input, String[] tokens) throws ParserException {
        if (tokens.length < 2) {
            throw new ParserException("Please specify a number");
        }

        int selection;
        try {
            selection = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException nfe) {
            throw new ParserException("Invalid number");
        }

        return new DoneCommand(selection - 1);
    }

    private Command prepareDelete(String input, String[] tokens) throws ParserException {
        if (tokens.length < 2) {
            throw new ParserException("Please specify a number");
        }

        int selection;
        try {
            selection = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException nfe) {
            throw new ParserException("Invalid number");
        }

        return new DeleteCommand(selection - 1);
    }

    private Command prepareFind(String input, String[] tokens) throws ParserException {
        if (tokens.length < 2) {
            throw new ParserException("Please specify what you are finding");
        }

        return new FindCommand(input.substring(5));
    }
}
