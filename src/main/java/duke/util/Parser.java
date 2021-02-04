package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parser checks for invalid input.
 */
public class Parser {

    /**
     * Checks if input from user is valid.
     * <br>Only accepts valid commands, followed by valid parameters.
     * @param input Input from user.
     * @throws DukeInputException If input is invalid.
     */
    public static void parseInput(String input) throws DukeInputException {
        String[] s = input.split(" ", 2);

        String command = s[0];
        String args = s.length == 2 ? s[1] : "";

        switch (command) {
        case "bye":
            break;
        case "list":
            break;
        case "done":
            checkValidDone(args);
            break;
        case "todo":
            checkValidTodo(args);
            break;
        case "deadline":
            checkValidDeadline(args);
            break;
        case "event":
            checkValidEvent(args);
            break;
        case "delete":
            checkValidDelete(args);
            break;
        case "save":
            break;
        case "load":
            break;
        case "help":
            break;
        case "search":
            checkValidSearch(args);
            break;
        default:
            throw new DukeInputException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Checks if input from text file is valid.
     * @param input Line from saved file.
     * @throws DukeInputException If input is invalid.
     */
    public static void checkImportFormat(String input) throws DukeInputException {
        String[] s = input.split(";", 3);

        if (s.length == 3 && (s[1].equals("0") || s[1].equals("1"))) {

            String[] args = s[2].split(";", 2);

            switch (s[0]) {
            case "T":
                checkValidTodo(args[0]);
                break;
            case "D":
                checkValidDeadline(String.join(" /by ", args));
                break;
            case "E":
                checkValidDeadline(String.join(" /at ", args));
                break;
            default:
                throw new DukeInputException("Not a valid Task type");
            }

        } else {
            throw new DukeInputException("Incorrect format");
        }
    }

    private static void checkValidDate(String s) throws DukeInputException {
        try {
            LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            throw new DukeInputException(
                String.format("\"%s\" is a wrong date format! Please use YYYY-MM-DD format.", s));
        }
    }

    private static void checkValidTodo(String s) throws DukeInputException {
        if (s.length() == 0) {
            throw new DukeInputException("Please include a description of the Todo!");
        }
    }

    private static void checkValidDeadline(String s) throws DukeInputException {
        String[] args = s.split(" /by ");

        if (args.length < 2) {
            throw new DukeInputException("Description and duedate should be separated by \"/by\"");
        } else if (args.length > 2) {
            throw new DukeInputException("Please do not use \"/by\" multiple times!");
        } else {
            checkValidDate(args[1]);
        }
    }

    private static void checkValidEvent(String s) throws DukeInputException {
        String[] args = s.split(" /at ");

        if (args.length < 2) {
            throw new DukeInputException("Description and date should separated by \"/at\"");
        } else if (args.length > 2) {
            throw new DukeInputException("Please do not use \"/at\" multiple times!");
        } else {
            checkValidDate(args[1]);
        }
    }

    private static void checkValidDone(String s) throws DukeInputException {
        if (s.length() == 0) {
            throw new DukeInputException("Please input a task number!");
        } else {
            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new DukeInputException(
                        String.format("\"%s\" is not a valid number!", s));
            }
        }
    }

    private static void checkValidDelete(String s) throws DukeInputException {
        if (s.length() == 0) {
            throw new DukeInputException("Please input a task number!");
        } else {
            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new DukeInputException(
                        String.format("\"%s\" is not a valid number!", s));
            }
        }
    }

    private static void checkValidSearch(String s) throws DukeInputException {
        if (s.length() == 0) {
            throw new DukeInputException("Enter a keyword to search!");
        }
    }

    /**
     * Checks if input is either a "y" (yes) or "n" (no).
     * @param s Input from user.
     * @throws DukeInputException If input is neither a "y" or "n".
     */
    public static void parseYesNo(String s) throws DukeInputException {
        if (!(s.equals("y") || s.equals("n"))) {
            throw new DukeInputException("Invalid input! Please key in either \"y\" or \"n\"");
        }
    }
}
