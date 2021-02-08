package vergil.components;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import vergil.types.commands.*;
import vergil.types.exceptions.VergilCommandException;
import vergil.types.exceptions.VergilException;

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
     * @param command the String command to be parsed.
     * @return a Command object representing the given command.
     * @throws VergilException if the command is unknown or invalid.
     */
    public Command parse(String command) throws VergilException {
        String index;
        String desc;
        String dateTime;
        String keywords;

        try {
            switch (command.split(" ")[0]) {
            case "bye":
                return new ByeCommand();

            case "list":
                return new ListCommand();

            case "done":
                index = command.split(" ")[1];
                return new DoneCommand(index);

            case "delete":
                index = command.split(" ")[1];
                return new DeleteCommand(index);

            case "todo":
                desc = command.split(" ", 2)[1];
                return new TodoCommand(desc);

            case "deadline":
                desc = command.split(" /by ")[0]
                        .split(" ", 2)[1];

                dateTime = command.split(" /by ")[1];

                return new DeadlineCommand(desc, dateTime);

            case "event":
                desc = command.split(" /at ")[0]
                        .split(" ", 2)[1];

                dateTime = command.split(" /at ")[1];

                return new EventCommand(desc, dateTime);

            case "find":
                keywords = command.split(" ", 2)[1];
                return new FindCommand(keywords);

            default:
                throw new VergilCommandException("Unable to resolve command.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new VergilCommandException("Command format is invalid.");
        }
    }
}
