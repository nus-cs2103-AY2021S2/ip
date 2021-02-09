package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Returns LocalDateTime from input string date format.
     *
     * @param date Input date from user.
     * @return Time specified by user.
     */
    public static LocalDateTime parseInputDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d Hmm"));
    }

    /**
     * Returns LocalDateTime from file string date format.
     *
     * @param date Input date from file
     * @return Time specified by file.
     */
    public static LocalDateTime parseFileDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy Hmm"));
    }

    /**
     * Parses the user input for Duke.
     * ALlows duke to identify which command user inputted.
     * Returns the unique command.
     *
     * @param input User input for the command for Duke.
     * @return Command of a type matching to the input from user.
     * @throws DukeWrongCommandException When command is unknown.
     */
    public static Command parseInput(String input) throws DukeWrongCommandException {
        String[] parsedInput = input.split(" ", 2);
        String commandStr = parsedInput[0];
        Command c;
        switch (commandStr) {
        case "bye":
            c = new ByeCommand();
            break;
        case "help":
            c = new HelpCommand();
            break;
        case "find":
            c = new FindCommand(parsedInput[1]);
            break;
        case "list":
            c = new ListCommand();
            break;
        case "done":
            c = new DoneCommand(Integer.parseInt(parsedInput[1]));
            break;
        case "todo":
        case "deadline":
        case "event":
            c = new AddCommand(commandStr, parsedInput[1]);
            break;
        case "delete":
            c = new DeleteCommand(Integer.parseInt(parsedInput[1]));
            break;
        default:
            throw new DukeWrongCommandException(commandStr);
        }
        assert c != null : "Command should not be null!";
        return c;
    }
}
