package duke;

import duke.command.*;

/**
 * A class that identify the keyword of given string.
 */
public class Parser {

    // Length of command.
    private static final int LENGTH_DEADLINE = "deadline ".length();
    private static final int LENGTH_DELETE = "delete ".length();
    private static final int LENGTH_EVENT = "event ".length();
    private static final int LENGTH_TODO = "todo ".length();
    private static final int LENGTH_DONE = "done ".length();
    private static final int LENGTH_FIND = "find ".length();
    private static final int LENGTH_GO = "go ".length();

    private static final String STRING_BYE = "bye";
    private static final String STRING_LIST = "list";
    private static final String STRING_DEADLINE = "deadline ";
    private static final String STRING_DELETE = "delete ";
    private static final String STRING_EVENT = "event ";
    private static final String STRING_TODO = "todo ";
    private static final String STRING_DONE = "done ";
    private static final String STRING_FIND = "find ";
    private static final String STRING_GO = "go ";
    private static final String STRING_PLACE = "place";

    private static final String ERROR_INVALID = "Invalid command.";

    /**
     * Returns the command according to the first word.
     * Arguments are not parsed in this method.
     *
     * @param str Input string.
     * @return Commands.
     * @throws DukeException If the first word is not any known command type.
     */
    public static Command parseCommand(String str) throws DukeException {
        int strLength = str.length();
        if (str.equalsIgnoreCase(STRING_BYE)) {
            return new ExitCommand();
        } else if (str.equalsIgnoreCase(STRING_LIST)) {
            return new ListCommand();
        } else if (str.length() >= LENGTH_DEADLINE
                && str.substring(0, LENGTH_DEADLINE).equalsIgnoreCase(STRING_DEADLINE)) {
            return new AddCommand(str, CommandType.Deadline);
        } else if (strLength >= LENGTH_DELETE
                && str.substring(0, LENGTH_DELETE).equalsIgnoreCase(STRING_DELETE)) {
            return new DeleteCommand(str);
        } else if (strLength >= LENGTH_EVENT
                && str.substring(0, LENGTH_EVENT).equalsIgnoreCase(STRING_EVENT)) {
            return new AddCommand(str, CommandType.Event);
        } else if (str.equalsIgnoreCase(STRING_PLACE)) {
            return new PlaceCommand();
        } else if (strLength >= LENGTH_TODO
                && str.substring(0, LENGTH_TODO).equalsIgnoreCase(STRING_TODO)) {
            return new AddCommand(str, CommandType.Todo);
        } else if (strLength >= LENGTH_DONE
                && str.substring(0, LENGTH_DONE).equalsIgnoreCase(STRING_DONE)) {
            return new DoneCommand(str);
        } else if (strLength >= LENGTH_FIND
                && str.substring(0, LENGTH_FIND).equalsIgnoreCase(STRING_FIND)) {
            return new FindCommand(str);
        } else if (strLength >= LENGTH_GO
                && str.substring(0, LENGTH_GO).equalsIgnoreCase(STRING_GO)) {
            return new GoCommand(str);
        } else {
            throw new DukeException(ERROR_INVALID);
        }
    }
}
