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
import data.Tag;
import data.TagList;
import data.Todo;

public class Parser {
    /**
     * Parses the given input to get the correct command
     *
     * @param input
     * @return Command
     * @throws ParserException
     */
    public Command parseCommand(String input) throws ParserException {
        String[] tokens = input.split(" ");
        String commandWord = tokens[0];

        switch (commandWord) {
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
        int minTokens = 2;

        if (tokens.length < minTokens) {
            throw new ParserException("Todo requires a description.");
        }

        String description = getDescription(input);
        TagList tags = getTags(input);

        Todo todo = new Todo(description, tags);
        return new AddTodoCommand(todo);
    }

    private Command prepareAddDeadline(String input, String[] tokens) throws ParserException {
        String bySwitch = "/by";

        String description = getDescription(input);

        TagList tags = getTags(input);

        LocalDate by;
        try {
            by = getDate(input, bySwitch);
        } catch (DateTimeException dte) {
            throw new ParserException("Deadline /by needs to be in a valid format (e.g. yyyy-MM-dd)");
        }

        if (by == null) {
            throw new ParserException("Deadline requires '/by' to be specified.");
        }

        Deadline deadline = new Deadline(description, tags, by);
        return new AddDeadlineCommand(deadline);
    }

    private Command prepareAddEvent(String input, String[] tokens) throws ParserException {
        String atSwitch = "/at";

        String description = getDescription(input);

        TagList tags = getTags(input);

        LocalDate at;
        try {
            at = getDate(input, atSwitch);
        } catch (DateTimeException dte) {
            throw new ParserException("Event /at needs to be in a valid format (e.g. yyyy-MM-dd)");
        }

        if (at == null) {
            throw new ParserException("Event requires '/at' to be specified.");
        }

        Event event = new Event(description, tags, at);
        return new AddEventCommand(event);
    }

    private Command prepareDone(String input, String[] tokens) throws ParserException {
        int minTokens = 2;

        if (tokens.length < minTokens) {
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
        int minTokens = 2;

        if (tokens.length < minTokens) {
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
        int minTokens = 2;

        if (tokens.length < minTokens) {
            throw new ParserException("Please specify what you are finding");
        }

        return new FindCommand(input.substring(5));
    }

    private String getDescription(String input) throws ParserException {
        int spaceOffset = 1;
        int firstSpaceIndex = input.indexOf(" ");

        int endOfDescription = input.indexOf("/", firstSpaceIndex + spaceOffset);
        if (endOfDescription == firstSpaceIndex + spaceOffset) {
            throw new ParserException("Description must come before all switches");
        }

        if (endOfDescription == -1) {
            endOfDescription = input.length();
        }

        return input.substring(firstSpaceIndex + spaceOffset, endOfDescription);
    }

    private TagList getTags(String input) {
        int tagOffset = 5;

        TagList tags = new TagList();

        int tagSwitchIndex = input.indexOf("/tag");
        if (tagSwitchIndex == -1) {
            return tags;
        }

        int endOfTags = input.indexOf("/", tagSwitchIndex + tagOffset);
        if (endOfTags == -1) {
            endOfTags = input.length();
        }

        String[] tagTokens = input.substring(tagSwitchIndex + tagOffset, endOfTags).split(" ");
        for (String tagToken : tagTokens) {
            tags.add(new Tag(tagToken));
        }

        return tags;
    }

    private LocalDate getDate(String input, String switchText) {
        int switchOffset = switchText.length() + 1;
        int switchIndex = input.indexOf(switchText);
        if (switchIndex == -1 || switchIndex + switchOffset > input.length()) {
            return null;
        }

        int endOfSwitch = input.indexOf("/", switchIndex + switchOffset);
        if (endOfSwitch == -1) {
            endOfSwitch = input.length();
        }

        String switchStr = input.substring(switchIndex + switchOffset, endOfSwitch).trim();
        if (switchStr.trim().equals("")) {
            return null;
        }

        LocalDate date = LocalDate.parse(switchStr);

        return date;
    }
}
