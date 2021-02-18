package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.ui.CommandType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.ui.CommandType.*;

public class Parser {

    /**
     * Parses the input string from the user and returns a Command according to the input.
     * @param inputString input string from user
     * @return Command object according to user's input
     * @throws DukeException Invalid user input
     */
    public static Command parse(String inputString) throws DukeException {
        String[] splitInput = inputString.split(" ", 2);

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

    /**
     * Parses an input String and returns a LocalDateTime in the "d/MM/yyyy Hmm" format.
     * @param dateTime date and time String specified by user input
     * @return LocalDateTime object in the "d/MM/yyyy Hmm" format.
     */
    public static LocalDateTime parseInputToDateTime(String dateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
        return LocalDateTime.parse(dateTime, format);
    }

    /**
     * Parses an input String according to the format of a saved Task in /data/duke.txt and
     * returns LocalDateTime object in the "d/MM/yyyy Hmm" format.
     * @param dateTime date and time String in the saved Task format
     * @return LocalDateTime object in the "d/MM/yyyy Hmm" format.
     */
    public static LocalDateTime parseSaveToDateTime(String dateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return LocalDateTime.parse(dateTime, format);
    }

    /**
     * Parses a LocalDateTime object and returns a String according to the output format.
     * @param dateTime LocalDateTime object
     * @return a String object that conforms to the output format
     */
    public static String parseDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return dateTime.format(pattern);
    }
}
