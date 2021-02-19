package vergil.components;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import vergil.types.commands.*;
import vergil.types.exceptions.VergilCommandException;
import vergil.types.exceptions.VergilException;
import vergil.types.exceptions.VergilFormatException;

/**
 * Represents a parser that parses commands given to Vergil.
 */
public class Parser {
    /**
     * Constructs a parser.
     */
    public Parser() {
        // Create a new parser.
    }

    /**
     * Parses a given command as String and returns a Command object representation
     * of that command.
     *
     * @param   command         the String command to be parsed.
     * @return                  a Command object representing the given command.
     * @throws  VergilException if the command is unknown or invalid.
     */
    public Command parse(String command) throws VergilException {
        String index;
        String desc;
        String dateTime;
        String keywords;

        switch (command.split(" ")[0]) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "done":
            try {
                index = command.split(" ")[1];
                return new DoneCommand(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new VergilFormatException(
                        "'done' commands must have the following format:\n"
                        + "done <task-number-in-the-list>"
                );
            }

        case "delete":
            try {
                index = command.split(" ")[1];
                return new DeleteCommand(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new VergilFormatException(
                    "'delete' commands must have the following format:\n"
                    + "delete <task-number-in-the-list>"
                );
            }

        case "todo":
            try {
                desc = command.split(" ", 2)[1];
                return new TodoCommand(desc);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new VergilFormatException(
                        "'todo' commands must have the following format:\n"
                        + "todo <task-description>"
                );
            }

        case "deadline":
            try {
                desc = command.split(" /by ")[0]
                        .split(" ", 2)[1];

                dateTime = command.split(" /by ")[1];

                return new DeadlineCommand(desc, dateTime);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new VergilFormatException(
                        "'deadline' commands must have the following format:\n"
                        + "deadline <task-description> /by <date: d/m/yyyy> <24-hours-time: hhmm>"
                );
            }

        case "event":
            try {
                desc = command.split(" /at ")[0]
                        .split(" ", 2)[1];

                dateTime = command.split(" /at ")[1];

                return new EventCommand(desc, dateTime);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new VergilFormatException(
                        "'event' commands must have the following format:\n"
                        + "event <task-description> /at <date: d/m/yyyy> <24-hours-time: hhmm>"
                );
            }

        case "find":
            try {
                keywords = command.split(" ", 2)[1];
                return new FindCommand(keywords);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new VergilFormatException(
                        "'find' commands must have the following format:\n"
                        + "find <search-keywords>"
                );
            }

        default:
            throw new VergilCommandException("I do not know that command.");
        }
    }
}
