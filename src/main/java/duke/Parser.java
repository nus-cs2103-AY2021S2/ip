package duke;

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
    private static final Pattern KEYWORD_AND_ARGUMENTS_PATTERN =
            Pattern.compile("(?<keyword>\\S+)(?<arguments>.*)");
    private static final Pattern SINGLE_INTEGER_ARGUMENT_PATTERN =
            Pattern.compile("(?<integerArgument>[0-9]+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern ADD_DEADLINE_ARGUMENTS_PATTERN =
            Pattern.compile("^(?<description>.+)\\s+/by\\s+(?<by>.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern ADD_EVENT_ARGUMENTS_PATTERN =
            Pattern.compile("^(?<description>.+)\\s+/at\\s+(?<at>.+)$", Pattern.CASE_INSENSITIVE);

    /*Keywords for each command type*/
    private static final String ADD_DEADLINE_COMMAND = "deadline";
    private static final String ADD_EVENT_COMMAND = "event";
    private static final String ADD_TODO_COMMAND = "todo";
    private static final String DELETE_TASK_COMMAND = "delete";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_DONE_COMMAND = "done";
    private static final String EXIT_COMMAND = "bye";
    private static final String FIND_COMMAND = "find";

    private static final String PARSE_ERROR_MESSAGE = "I'm sorry, " +
            "but I don't know what that means :-(";
    private static final String STORAGE_PARSE_ERROR_MESSAGE = "Please delete contents " +
            "of the file data.duke and try again.";
    /* Stores the string to Parse*/
    private final String inputCommand;

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
    public static Task parseTaskFromStoredFormat(String input) throws DukeParseException {
        Task parsedTask;
        String[] fields = input.split(" \\| ");
        String commandCode = fields[0];
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
        default:
            throw new DukeParseException(STORAGE_PARSE_ERROR_MESSAGE);
        }
        boolean isDone = (Integer.parseInt(fields[1]) == 1);
        if (isDone) {
            parsedTask.markAsDone();
        }
        return parsedTask;
    }



    /**
     * Parses the variable string to determine what type of Command Object should be created.
     *
     * @return A Command Object that represents the relevant action to execute,
     * @throws DukeParseException when the input String does not match any of the known command formats.
     */

    public Command parseCommand() throws DukeParseException {
        String command = getKeyWord();
        String arguments = getArguments();
        switch (command) {
        case ADD_DEADLINE_COMMAND:
            return parseAddDeadlineArguments(arguments);
        case ADD_EVENT_COMMAND:
            return parseAddEventArguments(arguments);
        case ADD_TODO_COMMAND:
            return parseAddToDoArguments(arguments);
        case DELETE_TASK_COMMAND:
            return parseDeleteCommandArguments(arguments);
        case MARK_DONE_COMMAND:
            return parseMarkDoneArguments(arguments);
        case LIST_COMMAND:
            return parseListCommandArguments(arguments);
        case EXIT_COMMAND:
            return parseExitCommandArguments(arguments);
        case FIND_COMMAND:
            return parseFindCommandArguments(arguments);
        default:
            throw new DukeParseException(PARSE_ERROR_MESSAGE);
        }
    }

    public String getArguments() throws DukeParseException {
        Matcher m = KEYWORD_AND_ARGUMENTS_PATTERN.matcher(inputCommand);
        if (m.matches()) {
            return m.group("arguments").trim();
        } else {
            throw new DukeParseException(PARSE_ERROR_MESSAGE);
        }
    }

    private void throwErrorIfNotEmpty(String input) throws DukeParseException {
        if (!input.equals("")) {
            throw new DukeParseException(PARSE_ERROR_MESSAGE);
        }
    }


    /**
     * Returns the Exit Command.
     *
     * @param arguments String to be parsed.
     * @return an exit Command.
     * @throws DukeParseException the string is not of the correct Exit Command format.
     */
    public Command parseExitCommandArguments(String arguments) throws DukeParseException {
        throwErrorIfNotEmpty(arguments);
        return new ExitCommand();
    }

    /**
     * Returns the List Command.
     *
     * @param arguments String to be parsed
     * @return a List Command.
     * @throws DukeParseException when string is not of the correct List Command format.
     */

    public Command parseListCommandArguments(String arguments) throws DukeParseException {
        throwErrorIfNotEmpty(arguments);
        return new ListCommand();
    }



    /**
     * Parses the input to see if it is of the MarkTaskCommand format,
     * if so returns the Command to mark task as done.
     *
     * @param arguments string to be Parsed.
     * @return Mark Task Command.
     * @throws DukeParseException When the Command cannot be parsed.
     */

    public void throwErrorIfEmpty(String arguments) throws DukeParseException {
        if (arguments.equals("")) {
            throw new DukeParseException("The input cannot be empty");
        }
    }

    public Matcher matchToInteger(String arguments) throws DukeParseException {
        throwErrorIfEmpty(arguments);
        Matcher m = SINGLE_INTEGER_ARGUMENT_PATTERN.matcher(arguments);
        if (!m.matches()) {
            // Unable to parse the string following "done "
            throw new DukeParseException("The input to the command must be integer.");
        } else {
            return m;
        }
    }

    public Command parseMarkDoneArguments(String arguments) throws DukeParseException {
        //for the case when "done" in the input string is followed by variable number of space.
        Matcher m = matchToInteger(arguments);
        int indexToMarkDone = Integer.parseInt(m.group("integerArgument"));
        return new MarkTaskCommand(indexToMarkDone);
    }



    /**
     * Parses the input to see if it is of the DeleteCommand format,
     * if so returns the Command to mark task as done.
     *
     * @param arguments string
     * @return Delete Command.
     * @throws DukeParseException when the delete is of the incorrect format.
     */

    public Command parseDeleteCommandArguments(String arguments) throws DukeParseException {
        Matcher m = matchToInteger(arguments);
        int indexToDelete = Integer.parseInt(m.group("integerArgument"));
        return new DeleteCommand(indexToDelete);
    }

    /**
     * Parses the input to see if it is of the AddToDoCommand format,
     * if so returns the Command to add ToDo Task.
     *
     * @param arguments string to be Parsed.
     * @return AddTodo Command.
     * @throws DukeParseException command Todo is  of the incorrect format.
     */

    public Command parseAddToDoArguments(String arguments) throws DukeParseException {
        throwErrorIfEmpty(arguments);
        Task t = new ToDo(arguments);
        return new AddCommand(t);
    }

    public Matcher matchAddDeadlineFormat(String arguments) throws DukeParseException {
        throwErrorIfEmpty(arguments);
        Matcher m = ADD_DEADLINE_ARGUMENTS_PATTERN.matcher(arguments);
        if (!m.matches()) {
            throw new DukeParseException("The deadline is of incorrect format.");
        } else {
            return m;
        }
    }

    /**
     * Parses the input to see if it is of the Add Deadline format,
     * if so returns the Command to add the Deadline.
     *
     * @param arguments string to be Parsed.
     * @return add Deadline Command
     * @throws DukeParseException when the string is of incorrect format.
     */

    public Command parseAddDeadlineArguments(String arguments) throws DukeParseException {
        Matcher m = matchAddDeadlineFormat(arguments);
        String description = m.group("description");
        String by = m.group("by");
        Task t = new Deadline(description, by);
        return new AddCommand(t);
    }

    public Matcher matchAddEventFormat(String arguments) throws DukeParseException {
        throwErrorIfEmpty(arguments);
        Matcher m = ADD_EVENT_ARGUMENTS_PATTERN.matcher(arguments);
        if (!m.matches()) {
            throw new DukeParseException("The event to add is of incorrect format.");
        } else {
            return m;
        }
    }

    /**
     * Parses the input to see if it is of the Add Event format,
     * if so returns the Command to add the Event task.
     *
     * @return addEvent Command
     * @throws DukeParseException when the event command is of incorrect format.
     */

    public Command parseAddEventArguments(String arguments) throws DukeParseException {
        Matcher m = matchAddEventFormat(arguments);
        String description = m.group("description");
        String at = m.group("at");
        Task t = new Event(description, at);
        return new AddCommand(t);
    }

    /**
     * Returns the first word of the inputCommand String given to the Parser, converted to lower case.
     *
     * @return the first word in the string, converted to lower case.
     */

    public String getKeyWord() throws DukeParseException{
        throwErrorIfEmpty(inputCommand);
        Matcher m = KEYWORD_AND_ARGUMENTS_PATTERN.matcher(this.inputCommand);
        m.matches();
        return m.group("keyword").toLowerCase();
    }



    /**
     * Parses the input to see if it fits the Find Command format,
     * if so returns the Command class to find the task.
     *
     * @param arguments input to parse.
     * @return A FindCommand
     * @throws DukeParseException when the find Command is of incorrect format.
     */

    public Command parseFindCommandArguments(String arguments) throws DukeParseException {
        throwErrorIfEmpty(arguments);
        return new FindTaskCommand(arguments);
    }

}
