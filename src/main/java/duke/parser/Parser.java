package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.ui.CommandType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.ui.CommandType.*;

public class Parser {

    public static Command parse(String inputString) throws DukeException {
        String[] splitInput = inputString.split(" ", 2);

        Command c;
        if (splitInput.length < 2) {
            splitInput = new String[]{splitInput[0], ""};
        }
        String argument = splitInput[1].trim();

        CommandType commandType = valueOf(splitInput[0].trim()
                .toUpperCase());

        switch (commandType) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand(argument);
        case DONE:
            return new DoneCommand(argument);
        case TODO:
            return new TodoCommand(argument);
        case DEADLINE:
            return new DeadlineCommand(argument);
        case EVENT:
            return new EventCommand(argument);
        case DELETE:
            return new DeleteCommand(argument);
        case FIND:
            return new FindCommand(argument);
        default:
            throw new DukeException("Sorry, I don't quite understand!");
        }
    }

    public static LocalDateTime parseInputToDateTime(String dateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
        return LocalDateTime.parse(dateTime, format);
    }

    public static LocalDateTime parseSaveToDateTime(String dateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return LocalDateTime.parse(dateTime, format);
    }

    public static String parseDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return dateTime.format(pattern);
    }
}
