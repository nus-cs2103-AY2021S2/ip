package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindTaskCommand;
import duke.command.ListCommand;
import duke.command.MarkTaskCommand;



/**
 * A Parser that provides certain key parsing methods on a input String to infer the relevant action to take,
 * which will be represented and returned as Command objects. Also has various static methods for searching strings
 * for date in broad format i.e ( yyyy-MM-dd).
 */

public class Parser {

    /* Pattern to get the first word of the String */
    private static final Pattern GET_KEYWORD = Pattern.compile("(\\S+).*");

    /*Keywords for each command type*/
    private static final String ADD_DEADLINE_COMMAND = "deadline";
    private static final String ADD_EVENT_COMMAND = "event";
    private static final String ADD_TODO_COMMAND = "todo";
    private static final String DELETE_TASK_COMMAND = "delete";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_DONE_COMMAND = "done";
    private static final String EXIT_COMMAND = "bye";
    private static final String FIND_COMMAND = "find";

    /* Stores the string to Parse*/
    private String inputCommand;

    /**
     * Constructor for a Parser Object.
     *
     * @param inputCommand String which the Parser will parse.
     */
    Parser(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    /**
     * Static method for parsing a special string representation of a Task which is used
     * to store the Task in hard disk. Returns the corresponding Task.
     *
     * @param input String representation of Task as it is stored in the hard disk.
     * @return the corresponding Task.
     */
    public static Task parseTaskFromStoredFormat(String input) {
        String[] fields = input.split(" \\| ");
        String commandCode = fields[0];
        Task parsedTask;
        switch (commandCode) {
        case ("T"):
            parsedTask = new ToDo(fields[2]);
            break;
        case ("D"):
            parsedTask = new Deadline(fields[2], fields[3]);
            break;
        case ("E"):
            parsedTask = new Event(fields[2], fields[3]);
            break;
        //default case throw a ParseError to be defined later...
        default:
            parsedTask = null;
        }
        boolean isDone = (Integer.parseInt(fields[1]) == 1);
        if (isDone && (parsedTask != null)) {
            parsedTask.markAsDone();
        }
        return parsedTask;
    }

    /**
     * Extracts date within the string in the format d - d - d, d stand for arbitrary number of digits.
     *
     * @param input string to be parsed.
     * @return The subtring containing the date only.
     */

    public static String extractDate(String input) {
        String regex = "\\d+[-]\\d+[-]\\d+";
        Pattern datePattern = Pattern.compile(regex);
        Matcher m = datePattern.matcher(input);
        if (m.find()) {
            return m.group(0);
        } else {
            return "";
        }
    }

    /**
     * parses the date and returns the string containing the date if it is of broad format ( yyyy-MM-dd)
     *
     * @param input string to be parsed.
     * @return string containing the date.
     */

    public static LocalDate parseDate(String input) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        return LocalDate.parse(input, dateTimeFormatter);
    }

    /**
     * Parses the variable string to determine what type of Command Object should be created.
     *
     * @return A Command Object that represents the relevant action to execute,
     * @throws DukeException when the input String does not match any of the known command formats.
     */

    public Command parseCommand() throws DukeException {
        String command = getKeyWord(inputCommand).toLowerCase();
        Task t;
        switch (command) {
        case ADD_DEADLINE_COMMAND:
            return parseAddDeadline(inputCommand);
        case ADD_EVENT_COMMAND:
            return parseAddEvent(inputCommand);
        case ADD_TODO_COMMAND:
            return parseAddToDo(inputCommand);
        case DELETE_TASK_COMMAND:
            return parseDelete(inputCommand);
        case MARK_DONE_COMMAND:
            return parseMarkDone(inputCommand);
        case LIST_COMMAND:
            return parseListCommand(inputCommand);
        case EXIT_COMMAND:
            return parseExitCommand(inputCommand);
        case FIND_COMMAND:
            return parseFindCommand(inputCommand);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns the Exit Command.
     *
     * @param inputCommand String to be parsed.
     * @return an exit Command.
     * @throws DukeException the string is not of the correct Exit Command format.
     */
    public Command parseExitCommand(String inputCommand) throws DukeException {
        String regex = EXIT_COMMAND + "\\s*";
        if (!inputCommand.toLowerCase().matches(regex)) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return new ExitCommand();
    }

    /**
     * Returns the List Command.
     *
     * @param inputCommand String to be parsed
     * @return a List Command.
     * @throws DukeException when string is not of the correct List Command format.
     */

    public Command parseListCommand(String inputCommand) throws DukeException {
        String regex = LIST_COMMAND + "\\s*";
        if (!inputCommand.toLowerCase().matches(regex)) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return new ListCommand();
    }

    /**
     * Parses the input to see if it is of the MsrkTaskCommand format,
     * if so returns the Command to mark task as done.
     *
     * @param input string to be Parsed.
     * @return Mark Task Command.
     * @throws DukeException When the Command cannot be parsed.
     */

    public Command parseMarkDone(String input) throws DukeException {
        //for the case when "done" in the input string is followed by variable number of space.
        if (input.toLowerCase().matches("^done\\s*$")) {
            throw new DukeException("The input cannot be empty.");
        }
        // "done" followed by at least one space and at least one number.
        String regex = "^done\\s+([0-9]+)$";
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()) {
            // Unable to parse the string following "done "
            throw new DukeException("The input for done must be integer.");
        }
        int indexToMarkDone = Integer.parseInt(m.group(1));
        return new MarkTaskCommand(indexToMarkDone);
    }


    // Methods for extracting dates and formatting dates.

    /**
     * Parses the input to see if it is of the DeleteCommand format,
     * if so returns the Command to mark task as done.
     *
     * @param input string
     * @return Delete Command.
     * @throws DukeException when the delete is of the incorrect format.
     */

    public Command parseDelete(String input) throws DukeException {
        //for the case when "delete" is followed by variable number of space.
        if (input.toLowerCase().matches("^delete\\s*$")) {
            throw new DukeException("The input cannot be empty.");
        }
        String regex = "^delete\\s+([0-9]+)$"; //delete followed by at least one space and one number.
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()) {
            throw new DukeException("The input for delete must be integer.");
        }
        int indexToDelete = Integer.parseInt(m.group(1));
        return new DeleteCommand(indexToDelete);
    }

    /**
     * Parses the input to see if it is of the AddToDoCommand format,
     * if so returns the Command to add ToDo Task.
     *
     * @param input string to be Parsed.
     * @return AddTodo Command.
     * @throws DukeException command Todo is  of the incorrect format.
     */

    public Command parseAddToDo(String input) throws DukeException {
        if (input.toLowerCase().matches("^todo\\s*$")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String regex = "^todo\\s+(.+)$";
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()) {
            throw new DukeException("The todo is of incorrect format.");
        }
        String description = m.group(1);
        Task t = new ToDo(description);
        return new AddCommand(t);
    }

    /**
     * Parses the input to see if it is of the Add Deadline format,
     * if so returns the Command to add the Deadline.
     *
     * @param input string to be Parsed.
     * @return add Deadline Command
     * @throws DukeException when the string is of incorrect format.
     */

    public Command parseAddDeadline(String input) throws DukeException {
        if (input.toLowerCase().matches("^deadline\\s*$")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String regex = "^deadline\\s+(.+)\\s+/by\\s+(.+)$";
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()) {
            throw new DukeException("The deadline is of incorrect format.");
        }
        String description = m.group(1);
        String by = m.group(2);
        Task t = new Deadline(description, by);
        return new AddCommand(t);
    }

    /**
     * Parses the input to see if it is of the Add Event format,
     * if so returns the Command to add the Event task.
     *
     * @return addEvent Command
     * @throws DukeException when the event command is of incorrect format.
     */

    public Command parseAddEvent(String input) throws DukeException {
        if (input.toLowerCase().matches("^event\\s*$")) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        String regex = "^event\\s+(.+)\\s+/at\\s+(.+)$";
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()) {
            throw new DukeException("The event is of incorrect format.");
        }
        String description = m.group(1);
        String at = m.group(2);
        Task t = new Event(description, at);
        return new AddCommand(t);
    }

    /**
     * Returns the first word of a string.
     *
     * @param inputCommand The string to extract the first word from.
     * @return the first word of the string.
     */

    public String getKeyWord(String inputCommand) {
        Matcher m = GET_KEYWORD.matcher(this.inputCommand);
        m.matches();
        return m.group(1);
    }

    /**
     * Parses the input to see if it fits the Find Command format,
     * if so returns the Command class to find the task.
     *
     * @param input input to parse.
     * @return A FindCommand
     * @throws DukeException when the find Command is of incorrect format.
     */

    public Command parseFindCommand(String input) throws DukeException {
        if (input.toLowerCase().matches("^" + FIND_COMMAND + "\\s*$")) {
            throw new DukeException("The description of a find Command cannot be empty.");
        }
        String regex = "(\\S+)\\s+(.+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(input);
        m.matches();
        return new FindTaskCommand(m.group(2).trim());
    }

    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }
}
