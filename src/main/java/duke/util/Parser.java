package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Parser ensures that only valid inputs are accepted.
 */
public class Parser {

    /**
     * Checks if input from user is valid and returns a pair of command and arugments.
     * <br>Only accepts valid commands, followed by valid parameters.
     *
     * @param input Input from user.
     * @return Pair of command and the arguments.
     * @throws DukeInputException If input is invalid.
     */
    public static Pair<Command, String> parseInput(String input) throws DukeInputException {
        // Do not allow ";" because save file uses semicolon as a delimiter.
        if (input.contains(";")) {
            throw new DukeInputException("Sorry! Semicolons \";\" are not allowed as input!");
        }

        String[] s = input.split(" ", 2);

        Command command = getCommand(s[0]);
        String args = s.length == 2 ? s[1] : "";

        checkValidArguments(command, args);

        return new Pair<>(command, args);
    }

    /**
     * Checks if input from text file is valid and creates the task.
     *
     * @param input Import statement from saved file.
     * @return Imported task.
     * @throws DukeInputException If input is invalid.
     */
    public static Task parseImport(String input) throws DukeInputException {
        // Import statments must have at least 4 ";"
        String[] args = input.split(";", 4);

        if (args.length != 4) {
            throw new DukeInputException("Wrong number of arguments");
        }
        if (isNotValidBinary(args[1])) {
            throw new DukeInputException("isDone column should be 0 or 1");
        }
        if (isNotValidBinary(args[2])) {
            throw new DukeInputException("Priority column should be 0 or 1");
        }

        // Deadline and Event will have an additional ";"
        String[] s = args[3].split(";", 2);

        switch (args[0]) {
        case "T":
            checkValidTodo(s[0]);
            return Todo.importData(input.split(";"));
        case "D":
            checkValidDeadline(String.join(" /by ", s));
            return Deadline.importData(input.split(";"));
        case "E":
            checkValidEvent(String.join(" /at ", s));
            return Event.importData(input.split(";"));
        default:
            throw new DukeInputException("Not a valid Task type");
        }
    }

    /**
     * Checks if input is either a "y" (yes) or "n" (no).
     *
     * @param input Input from user.
     * @return True if input is "y", false if input is "n".
     * @throws DukeInputException If input is neither a "y" or "n".
     */
    public static boolean parseYesNo(String input) throws DukeInputException {
        if (!(input.equals("y") || input.equals("n"))) {
            throw new DukeInputException("Invalid input! Please key in either \"y\" or \"n\"");
        }
        return input.equals("y");
    }

    private static Command getCommand(String input) throws DukeInputException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeInputException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void checkValidArguments(Command cmd, String args) throws DukeInputException {

        if (args.equals("-h")) {
            return;
        }

        switch(cmd) {
        case BYE:
            checkNoArgument(args);
            break;
        case CLEAR:
            checkNoArgument(args);
            break;
        case DEADLINE:
            checkValidDeadline(args);
            break;
        case DELETE:
            checkValidDoneOrDelete(args);
            break;
        case DONE:
            checkValidDoneOrDelete(args);
            break;
        case EVENT:
            checkValidEvent(args);
            break;
        case HELP:
            checkNoArgument(args);
            break;
        case HIGHPRIORITY:
            checkValidNumber(args);
            break;
        case LIST:
            checkNoArgument(args);
            break;
        case LOAD:
            checkNoArgument(args);
            break;
        case LOWPRIORITY:
            checkValidNumber(args);
            break;
        case SAMPLE:
            checkNoArgument(args);
            break;
        case SAVE:
            checkNoArgument(args);
            break;
        case SEARCH:
            checkValidSearch(args);
            break;
        case SORT:
            checkNoArgument(args);
            break;
        case TODO:
            checkValidTodo(args);
            break;
        default:
            break;
        }
    }

    private static void checkValidDate(String date) throws DukeInputException {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeInputException(
                String.format("\"%s\" is a wrong date format! Please use YYYY-MM-DD format.", date));
        }
    }

    private static void checkNoArgument(String args) throws DukeInputException {
        if (!args.isEmpty()) {
            throw new DukeInputException(String.format("%s is not a valid argument!", args));
        }
    }

    private static void checkValidTodo(String args) throws DukeInputException {
        if (args.isEmpty()) {
            throw new DukeInputException("Please include a description of the Todo!");
        }
    }

    private static void checkValidDeadline(String s) throws DukeInputException {
        String[] args = s.split(" /by ");

        if (args.length < 2) {
            throw new DukeInputException("Description and duedate should be separated by \"/by\"");
        } else if (args.length > 2) {
            throw new DukeInputException("Please do not use \"/by\" multiple times!");
        }

        checkValidDate(args[1]);
    }

    private static void checkValidEvent(String s) throws DukeInputException {
        String[] args = s.split(" /at ");

        if (args.length < 2) {
            throw new DukeInputException("Description and date should separated by \"/at\"");
        } else if (args.length > 2) {
            throw new DukeInputException("Please do not use \"/at\" multiple times!");
        }

        checkValidDate(args[1]);
    }

    private static void checkValidDoneOrDelete(String args) throws DukeInputException {
        if (args.isEmpty()) {
            throw new DukeInputException("Please input a task number!");
        }

        String[] indexes = args.split(" ");

        // Check each number is seperated by single space and is valid
        for (String idx : indexes) {
            if (idx.isEmpty()) {
                throw new DukeInputException("Please leave only one space between numbers!");
            }
            checkValidNumber(idx);
        }

        // Check for duplicate numbers
        Arrays.sort(indexes);
        for (int i = 1; i < indexes.length; i++) {
            if (indexes[i].equals(indexes[i - 1])) {
                throw new DukeInputException("Please do not input duplicate numbers!");
            }
        }
    }

    private static void checkValidSearch(String args) throws DukeInputException {
        if (args.isEmpty()) {
            throw new DukeInputException("Enter a keyword to search!");
        }
    }

    private static void checkValidNumber(String idx) throws DukeInputException {
        try {
            Integer.parseInt(idx);
        } catch (NumberFormatException e) {
            throw new DukeInputException(String.format("\"%s\" is not a valid number!", idx));
        }
    }

    private static boolean isNotValidBinary(String idx) {
        return !(idx.equals("0") || idx.equals("1"));
    }
}
