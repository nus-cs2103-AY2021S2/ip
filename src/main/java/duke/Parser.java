package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.CommandException;

/**
 * Parser class to parse the input from users and return the appropriate instance of a command
 */
public class Parser {

    /**
     * Constructor for a Parser
     */
    public Parser() {
    }

    /**
     * Parses the line input by the user
     * @param line the line input by the uer
     * @return A command corresponding to the input
     * @throws CommandException when command is not understood
     * @throws IndexOutOfBoundsException when command delete is called on an index that is out of bounds
     * @throws NumberFormatException when command delete is called on an index that is not a number
     */
    public Command parse(String line) throws CommandException, IndexOutOfBoundsException, NumberFormatException {
        String command = line.split(" ")[0];
        try {
            switch (command) {
            case "bye":
                return new ByeCommand();
            case "list": {
                return new ListCommand();
            }
            case "find": {
                return parseFind(line);
            }
            case "done": {
                return parseDone(line);
            }
            case "delete": {
                return parseDelete(line);
            }
            case "todo": {
                return parseTodo(line);
            }
            case "deadline": {
                return parseDeadline(line);
            }
            case "event": {
                return parseEvent(line);
            }
            default: {
                throw new CommandException("I don't understand");
            }
            }

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CommandException("Please enter a valid value");
        }
    }

    private Command parseFind(String line) throws CommandException {
        String[] ar = line.split(" ");
        if (ar.length > 2) {
            throw new CommandException("I can only handle one keyword!");
        } else if (ar.length == 1) {
            throw new CommandException("What keyword are you searching for?");
        } else {
            return new FindCommand(ar[1]);
        }
    }

    private Command parseDone(String line) throws CommandException {
        String[] ar = line.split(" ", 2);
        if (ar.length == 1) {
            throw new CommandException("Which task are you done with?");
        }
        line = line.split(" ", 2)[1];
        int index = Integer.parseInt(line) - 1;
        return new DoneCommand(index);
    }

    private Command parseDelete(String line) throws CommandException {
        String[] ar = line.split(" ", 2);
        if (ar.length == 1) {
            throw new CommandException("Which task are you deleting?");
        }
        line = line.split(" ", 2)[1];
        int index = Integer.parseInt(line) - 1;
        return new DeleteCommand(index);
    }

    private Command parseTodo(String line) throws CommandException {
        String[] ar = line.split(" ", 2);
        if (ar.length == 1) {
            throw new CommandException("I can't add an empty task to the list!");
        }
        line = line.split(" ", 2)[1];
        return new TodoCommand(line);
    }

    private Command parseDeadline(String line) throws CommandException {
        String[] ar = line.split(" ", 2);
        if (ar.length == 1) {
            throw new CommandException("I can't add an empty task to the list!");
        }
        line = line.split(" ", 2)[1];
        String[] result = line.split("/by ");
        if (result.length == 1) {
            throw new CommandException("Er... when do you need to finish this /by?");
        }
        try {
            LocalDate date = LocalDate.parse(result[1]);
            return new DeadlineCommand(result[0], date);
        } catch (DateTimeParseException e) {
            throw new CommandException("Please input a valid date as yyyy-mm-dd");
        }
    }

    private Command parseEvent(String line) throws CommandException {
        String[] ar = line.split(" ", 2);
        if (ar.length == 1) {
            throw new CommandException("I can't add an empty task to the list!");
        }
        line = line.split(" ", 2)[1];
        String[] result = line.split("/at ");
        if (result.length == 1) {
            throw new CommandException("Er... /at what time does this event start?");
        }

        try {
            LocalDate date = LocalDate.parse(result[1]);
            return new EventCommand(result[0], date);
        } catch (DateTimeParseException e) {
            throw new CommandException("Please input your date as yyyy-mm-dd");
        }
    }
}
