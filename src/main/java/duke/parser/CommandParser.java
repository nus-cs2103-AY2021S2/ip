package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindTaskCommand;
import duke.command.ListCommand;
import duke.command.MarkTaskCommand;
import duke.exceptions.DukeCommandParseException;
import duke.exceptions.DukeDateParseException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;



/**
 * A Parser that provides certain key parsing methods on a input String to infer the relevant action to take,
 * which will be represented and returned as Command objects. Also has various static methods for searching strings
 * for date in broad format i.e ( yyyy-MM-dd).
 */

public class CommandParser {

    /* Pattern to get the first word of the String */
    private static final Pattern KEYWORD_AND_ARGUMENTS_PATTERN =
            Pattern.compile("(?<keyword>\\S+)(?<arguments>.*)");
    private static final Pattern SINGLE_INTEGER_ARGUMENT_PATTERN =
            Pattern.compile("^(?<integerArgument>[0-9]+)$", Pattern.CASE_INSENSITIVE);
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
    private static final String PARSE_ERROR_MESSAGE = "I'm sorry, "
            + "but I don't know what that means :-(";
    private static final String DEADLINE_FORMAT_ERROR_MESSAGE = "The deadline is of incorrect format.";
    private static final String EVENT_FORMAT_ERROR_MESSAGE = "The event to add is of incorrect format.";
    private static final String EMPTY_INPUT_ERROR_MESSAGE = "The input cannot be empty";
    private static final String INTEGER_FORMAT_ERROR_MESSAGE = "The input to the command must be integer.";

    /* Stores the string to Parse*/
    private final String inputCommand;

    /**
     * Constructor for a Parser Object.
     *
     * @param inputCommand String which the Parser will parse.
     */
    public CommandParser(String inputCommand) {
        this.inputCommand = inputCommand.trim();
    }


    /**
     * Parses the variable string to determine what type of Command Object should be created.
     *
     * @return A Command Object that represents the relevant action to execute,
     * @throws DukeCommandParseException when the input String does not match any of the known command formats.
     */


    public Command parseCommand() throws DukeCommandParseException, DukeDateParseException {
        String commandWord = getKeyWord();
        assert !this.inputCommand.equals(""); //check that empty string is handled by getKeyWord
        String arguments = getArguments();
        switch (commandWord) {
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
            throw new DukeCommandParseException(PARSE_ERROR_MESSAGE);
        }
    }

    /**
     * Gets the argument string following the command word.
     * @return the argument string
     * @throws DukeCommandParseException if it does not match the command format.
     */

    public String getArguments() throws DukeCommandParseException {
        Matcher m = KEYWORD_AND_ARGUMENTS_PATTERN.matcher(this.inputCommand);
        if (m.matches()) {
            return m.group("arguments").trim();
        } else {
            throw new DukeCommandParseException(PARSE_ERROR_MESSAGE);
        }
    }

    /**
     * check if the string is empty. Throws error if its not empty.
     * @param input string to be checked
     * @throws DukeCommandParseException if string is not ""
     */
    private void throwErrorIfNotEmpty(String input) throws DukeCommandParseException {
        if (!input.equals("")) {
            throw new DukeCommandParseException(PARSE_ERROR_MESSAGE);
        }
    }


    /**
     * Returns the Exit Command.
     *
     * @param arguments String to be parsed.
     * @return an exit Command.
     * @throws DukeCommandParseException the string is not of the correct Exit Command format.
     */
    public Command parseExitCommandArguments(String arguments) throws DukeCommandParseException {
        throwErrorIfNotEmpty(arguments);
        return new ExitCommand();
    }

    /**
     * Returns the List Command.
     *
     * @param arguments String to be parsed
     * @return a List Command.
     * @throws DukeCommandParseException when string is not of the correct List Command format.
     */

    public Command parseListCommandArguments(String arguments) throws DukeCommandParseException {
        throwErrorIfNotEmpty(arguments);
        return new ListCommand();
    }

    /**
     * Check if the string is nonempty. Throws error is string is empty.
     * @param arguments string to be checked
     * @throws DukeCommandParseException if the string is ""
     */

    private void throwErrorIfEmpty(String arguments) throws DukeCommandParseException {
        if (arguments.equals("")) {
            throw new DukeCommandParseException(EMPTY_INPUT_ERROR_MESSAGE);
        }
    }

    /**
     * Matches a string if it is just a string literal of an integer value,
     * and returns the matcher. Else throws exception.
     * @param arguments the strng to be matched.
     * @return matcher the matcher that is successfully matched to an integer literal.
     * @throws DukeCommandParseException if there is no match found.
     */

    Matcher matchToInteger(String arguments) throws DukeCommandParseException {
        throwErrorIfEmpty(arguments);
        Matcher m = SINGLE_INTEGER_ARGUMENT_PATTERN.matcher(arguments);
        if (!m.matches()) {
            // Unable to parse the string to integer
            throw new DukeCommandParseException(INTEGER_FORMAT_ERROR_MESSAGE);
        } else {
            return m;
        }
    }

    /**
     * Parses the input to see if it is of the MarkTaskCommand format,
     * if so returns the Command to mark task as done.
     *
     * @param arguments string to be Parsed.
     * @return Mark Task Command.
     * @throws DukeCommandParseException When the Command cannot be parsed.
     */


    public Command parseMarkDoneArguments(String arguments) throws DukeCommandParseException {
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
     * @throws DukeCommandParseException when the delete is of the incorrect format.
     */

    public Command parseDeleteCommandArguments(String arguments) throws DukeCommandParseException {
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
     * @throws DukeCommandParseException command Todo is  of the incorrect format.
     */

    public Command parseAddToDoArguments(String arguments) throws DukeCommandParseException {
        throwErrorIfEmpty(arguments);
        Task t = new ToDo(arguments);
        return new AddCommand(t);
    }

    /**
     * Matches a given argument string to the format required of a valid Add Deadline command.
     * The matcher returned will then be
     * used to extract out the arguments needed by parseAddEvent.
     *
     * @param arguments the string to match
     * @return matcher containing extracted arguments.
     * @throws DukeCommandParseException when the string does not match to a valid argument.
     */


    Matcher matchAddDeadlineFormat(String arguments) throws DukeCommandParseException {
        throwErrorIfEmpty(arguments);
        Matcher m = ADD_DEADLINE_ARGUMENTS_PATTERN.matcher(arguments);
        if (!m.matches()) {
            throw new DukeCommandParseException(DEADLINE_FORMAT_ERROR_MESSAGE);
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
     * @throws DukeCommandParseException when the string is of incorrect format.
     */

    public Command parseAddDeadlineArguments(String arguments)
            throws DukeCommandParseException, DukeDateParseException {
        Matcher m = matchAddDeadlineFormat(arguments);
        String description = m.group("description");
        String by = m.group("by");
        Task t = new Deadline(description, by);
        return new AddCommand(t);
    }

    /**
     * Matches a given argument string to the format required of a valid Add Event command.
     * The matcher returned will then be
     * used to extract out the arguments needed by parseAddEvent.
     *
     * @param arguments the string to match
     * @return matcher containing extracted arguments.
     * @throws DukeCommandParseException when the string does not match to a valid argument.
     */

    Matcher matchAddEventFormat(String arguments) throws DukeCommandParseException {
        throwErrorIfEmpty(arguments);
        Matcher m = ADD_EVENT_ARGUMENTS_PATTERN.matcher(arguments);
        if (!m.matches()) {
            throw new DukeCommandParseException(EVENT_FORMAT_ERROR_MESSAGE);
        } else {
            return m;
        }
    }

    /**
     * Parses the input to see if it is of the Add Event format,
     * if so returns the Command to add the Event task.
     *
     * @return addEvent Command
     * @throws DukeCommandParseException when the event command is of incorrect format.
     */

    public Command parseAddEventArguments(String arguments) throws DukeCommandParseException, DukeDateParseException {
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

    public String getKeyWord() throws DukeCommandParseException {
        throwErrorIfEmpty(this.inputCommand);
        Matcher m = KEYWORD_AND_ARGUMENTS_PATTERN.matcher(this.inputCommand);
        boolean isMatch = m.matches();
        //there will definitely be a match because string is non empty and hence contains a word.
        assert isMatch == true;
        return m.group("keyword").toLowerCase();
    }



    /**
     * Parses the input to see if it fits the Find Command format,
     * if so returns the Command class to find the task.
     *
     * @param arguments input to parse.
     * @return A FindCommand
     * @throws DukeCommandParseException when the find Command is of incorrect format.
     */

    public Command parseFindCommandArguments(String arguments) throws DukeCommandParseException {
        throwErrorIfEmpty(arguments);
        return new FindTaskCommand(arguments);
    }

}
